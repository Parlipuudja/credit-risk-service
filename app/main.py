from contextlib import asynccontextmanager

import joblib
import numpy as np
from fastapi import FastAPI

from app.schemas import CreditInput, FeatureImportance, PredictionOutput

NUM_FEATURES = [
    "LIMIT_BAL", "AGE", "BILL_AMT1", "BILL_AMT2", "BILL_AMT3", "BILL_AMT4",
    "BILL_AMT5", "BILL_AMT6", "PAY_AMT1", "PAY_AMT2", "PAY_AMT3",
    "PAY_AMT4", "PAY_AMT5", "PAY_AMT6",
]
CAT_FEATURES = ["SEX", "EDUCATION", "MARRIAGE", "PAY_0", "PAY_2", "PAY_3", "PAY_4", "PAY_5", "PAY_6"]

state = {}


@asynccontextmanager
async def lifespan(app: FastAPI):
    state["model"] = joblib.load("model.pkl")
    state["scaler"] = joblib.load("num_scaler.pkl")
    state["feature_names"] = joblib.load("feature_names.pkl")
    yield


app = FastAPI(lifespan=lifespan)


@app.post("/predict", response_model=PredictionOutput)
async def predict(data: CreditInput):
    d = data.model_dump()
    num_vals = np.array([[d[f] for f in NUM_FEATURES]])
    cat_vals = np.array([[d[f] for f in CAT_FEATURES]])
# 
    num_scaled = state["scaler"].transform(num_vals)
    X = np.hstack([num_scaled, cat_vals])

    prob = state["model"].predict_proba(X)[0][1]
    label = "high_risk" if prob >= 0.5 else "low_risk"

    importances = sorted(
        zip(state["feature_names"], state["model"].feature_importances_),
        key=lambda x: x[1], reverse=True,
    )[:5]

    return PredictionOutput(
        risk_score=round(float(prob), 4),
        prediction=label,
        top_features=[FeatureImportance(feature=f, importance=round(float(v), 4)) for f, v in importances],
    )
