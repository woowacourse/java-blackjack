package blackjack.domain;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_MIN_TOTAL = 17;

    private BettingMoney bettingMoney = new BettingMoney();

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card showFirstCard() {
        return super.getHoldingCard().pickFirstCard();
    }

    public boolean isFinished() {
        return super.getHoldingCard().calculateTotal() >= DEALER_MIN_TOTAL;
    }

    public GameResult judgeResult(Player player) {
        if(playerBust(player) || bothNotBustAndDealerTotalLarger(player) || onlyDealerBlackJack(player)) {
            return GameResult.WIN;
        }
        if(onlyDealerBust(player) || bothNotBustAndPlayerTotalLarger(player)) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public void exchangeBettingMoney(int money) {
        bettingMoney.exchangeMoney(money);
    }

    public int getRevenue() {
        return bettingMoney.money;
    }

    private boolean playerBust(Player player) {
        return player.getHoldingCard().isBust();
    }

    private boolean dealerBust() {
        return super.getHoldingCard().isBust();
    }

    private boolean bothNotBustAndDealerTotalLarger(Player player) {
        if (dealerBust() || playerBust(player)) {
            return false;
        }
        return super.getHoldingCard().calculateTotal() > player.getHoldingCard().calculateTotal();
    }

    private boolean onlyDealerBust(Player player) {
        return dealerBust() && !playerBust(player);
    }

    private boolean bothNotBustAndPlayerTotalLarger(Player player) {
        if (dealerBust() || playerBust(player)) {
            return false;
        }
        return super.getHoldingCard().calculateTotal() < player.getHoldingCard().calculateTotal();
    }

    private boolean onlyDealerBlackJack(Player player) {
        return super.getHoldingCard().isBlackJack() && !player.getHoldingCard().isBlackJack();
    }

    private static class BettingMoney {
        private int money;

        private BettingMoney() {
            this.money = 0;
        }

        private void exchangeMoney(int money) {
            this.money -= money;
        }
    }
}
