package blackjack.domain;

public class Player extends Participant {
    private boolean finish = false;

    private BettingMoney bettingMoney;

    public Player(String name) {
        super(name);
    }

    public boolean isNeedBettingMoney() {
        if (bettingMoney == null) {
            return true;
        }
        return false;
    }

    public void putBettingMoney(int money) {
        bettingMoney = new BettingMoney(money);
    }

    public void closeTurn() {
        finish = true;
    }

    public boolean isFinished() {
        return super.getHoldingCard().isFullScoreOrBust() || finish;
    }

    public int moneyToExchange(GameResult gameResult) {
        if (super.getHoldingCard().isBlackJack() && gameResult == GameResult.WIN) {
            return bettingMoney.calculateRevenue(1.5);
        }
        if (gameResult == GameResult.WIN) {
            return bettingMoney.calculateRevenue(1.0);
        }
        if (gameResult == GameResult.LOSE) {
            return bettingMoney.calculateRevenue(-1.0);
        }
        return bettingMoney.revenue;
    }

    public int getRevenue() {
        return bettingMoney.revenue;
    }

    private static class BettingMoney {
        private final int initial;
        private int revenue = 0;

        private BettingMoney(int initial) {
            this.initial = initial;
        }

        private int calculateRevenue(double rate) {
            revenue = (int) (initial * rate);
            return revenue;
        }
    }
}
