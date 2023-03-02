package domain.player;

import domain.area.CardArea;
import domain.card.Card;

public class Dealer extends Player {

    public Dealer(final Name name, final CardArea cardArea) {
        super(name, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.calculate() <= 16;
    }

    public Card firstCard() {
        return cardArea.cards().get(0);
    }
}
