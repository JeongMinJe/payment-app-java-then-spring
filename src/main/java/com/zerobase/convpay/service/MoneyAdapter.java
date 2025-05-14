package com.zerobase.convpay.service;

public class MoneyAdapter {
    public MoneyUseResult use(Integer payAmount){
        System.out.println("MoneyAdapter.use" + payAmount);

        if (payAmount > 1000_000) {
            return MoneyUseResult.USE_FAIL;
        }

        return MoneyUseResult.USE_SUCCESS;
    }

    public void useCancel(){

    }
}
