package com.zerobase.convpay.service;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.dto.PayResult;

public class ConveniencePayService {
    private final MoneyAdapter moneyAdapter = new MoneyAdapter();

    public PayResponse pay(PayRequest payRequest) {
        MoneyUseResult moneyUseResult =
            moneyAdapter.use(payRequest.getPayAmount());


        if(moneyUseResult == MoneyUseResult.USE_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }

        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());

    }

    public void payCancel() {

    }
}
