package com.example.plantsparadise.core.callbacks;

public interface SuccessOrFailureCallback {
    void onSuccess(Object successObject);
    void onFailure(Object errorObject);
}
