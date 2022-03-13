package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer{

    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public boolean isOverThan(int number) {
        return getCardsNumberSum() > number;
    }

    public Card getFirstCard() {
        return super.getCards().get(0);
    }
}
