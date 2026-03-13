package model.result;

public class DealerWinning {
    private Integer totalBet = 0;

    public void increase(Integer betAmount) {
        totalBet += betAmount;
    }

    public Integer getTotalBet() {
        return totalBet;
    }
}
