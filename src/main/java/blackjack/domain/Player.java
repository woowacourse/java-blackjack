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

    public int bettingRevenue(GameResult gameResult) {
        if (super.getHoldingCard().isBlackJack() && gameResult == GameResult.WIN) {
            return (int) (bettingMoney.money * 1.5);
        }
        return bettingMoney.calculateRevenue(gameResult);
    }

    private static class BettingMoney {
        private final int money;

        private BettingMoney(int money) {
            this.money = money;
        }

        private int calculateRevenue(GameResult gameResult) {
            if (gameResult == GameResult.WIN) {
                return money;
            }
            if (gameResult == GameResult.LOSE) {
                return money * -1;
            }
            return 0;
        }
    }
}
