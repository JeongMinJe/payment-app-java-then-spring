package com.zerobase.convpay.service;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.dto.PayResult;

public class ConveniencePayService {
    public PayResponse pay(PayRequest payRequest) {
        return new PayResponse(PayResult.SUCCESS, 100);
    }

    public void payCancel() {

    }
}
