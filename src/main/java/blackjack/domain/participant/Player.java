package blackjack.domain.participant;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(String name, int money) {
        super(name);
        bettingMoney = new BettingMoney(money);
    }

    public int getProfit(Dealer dealer) {
        int expectedProfit = (int) (bettingMoney.getMoney() * state.earningsRate());
        if (isBlackjack()) {
            return getProfitWhenBlackjack(expectedProfit, dealer);
        }
        if (isBust()) {
            return expectedProfit;
        }
        return getProfitWhenStay(expectedProfit, dealer);
    }

    private int getProfitWhenBlackjack(int expectedProfit, Dealer dealer) {
        if (dealer.isBlackjack()) {
            return 0;
        }
        return expectedProfit;
    }

    private int getProfitWhenStay(int expectedProfit, Dealer dealer) {
        int dealerScore = dealer.getCardsScore();
        int playerScore = getCardsScore();
        if (dealer.isBust() || playerScore > dealerScore) {
            return expectedProfit;
        }
        if (dealer.isBlackjack() || playerScore < dealerScore) {
            return expectedProfit * -1;
        }
        return 0;
    }
}
