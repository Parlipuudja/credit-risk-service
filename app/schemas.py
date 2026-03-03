from pydantic import BaseModel


class CreditInput(BaseModel):
    LIMIT_BAL: float
    AGE: float
    BILL_AMT1: float
    BILL_AMT2: float
    BILL_AMT3: float
    BILL_AMT4: float
    BILL_AMT5: float
    BILL_AMT6: float
    PAY_AMT1: float
    PAY_AMT2: float
    PAY_AMT3: float
    PAY_AMT4: float
    PAY_AMT5: float
    PAY_AMT6: float
    SEX: int
    EDUCATION: int
    MARRIAGE: int
    PAY_0: int
    PAY_2: int
    PAY_3: int
    PAY_4: int
    PAY_5: int
    PAY_6: int


class FeatureImportance(BaseModel):
    feature: str
    importance: float


class PredictionOutput(BaseModel):
    risk_score: float
    prediction: str
    top_features: list[FeatureImportance]
