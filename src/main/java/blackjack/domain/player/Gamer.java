
package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Gamer extends Player {

    private final BettingMoney bettingMoney;

    public Gamer(Name name, Cards cards) {
        this(name, cards, new BettingMoney(1));
    }

    public Gamer(Name name, Cards cards, BettingMoney bettingMoney) {
        super(name, cards);
        this.bettingMoney = bettingMoney;
    }

    public int getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished();
    }

    @Override
    public void draw(Card card) {
        state = state.draw(card);
    }

    public int getProfit(Dealer dealer) {
        int profit = (int)state.calculateProfit(bettingMoney);
        if (isBlackJack() && dealer.isBlackJack()) {
            return 0;
        }
        if (isBlackJack() && !dealer.isBlackJack()) {
            return profit;
        }
        if (!isBlackJack() && dealer.isBlackJack()) {
            return getBettingMoney() * (-1);
        }
        if (isBust() && !dealer.isBlackJack()) {
            return getBettingMoney() * (-1);
        }
        if (!isBust() && dealer.isBust()) {
            return profit;
        }
        return getProfitIfAllStay(dealer);
    }

    private int getProfitIfAllStay(Dealer dealer) {
        int profit = (int)state.calculateProfit(bettingMoney);
        if (calculateScore() > dealer.calculateScore()) {
            return profit;
        }
        if (calculateScore() < dealer.calculateScore()) {
            return profit * (-1);
        }
        return 0;
    }
}
