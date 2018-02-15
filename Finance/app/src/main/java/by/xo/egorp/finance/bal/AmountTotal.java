package by.xo.egorp.finance.bal;

import by.xo.egorp.finance.dao.Currency;

public class AmountTotal {
    private Currency currency;
    private Float amountTotal;

    AmountTotal() {
        currency = new Currency();
        amountTotal = 0f;
    }

    AmountTotal(Currency currency, Float amountTotal) {
        this.currency = currency;
        this.amountTotal = amountTotal;
    }


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Float getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Float amountTotal) {
        this.amountTotal = amountTotal;
    }

    public void AddSum(float sum) {
        amountTotal += sum;
    }


}
