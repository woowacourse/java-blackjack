package domain.result;

public class DealerResult {

    private double dealerResult;

    DealerResult() {
        this.dealerResult = 0;
    }

    void resultContributor(double playerResultMoney) {
        this.dealerResult -= playerResultMoney;
    }

    double getDealerResult() {
        return dealerResult;
    }

}
