
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

    public int profit() {
        return (int) state.calculateProfit(bettingMoney);
    }
}
