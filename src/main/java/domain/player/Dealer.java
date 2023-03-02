package domain.player;

import domain.area.CardArea;
import domain.card.Card;

public class Dealer extends Participant {

    private static final Name DEALER_NAME = Name.of("딜러");

    public Dealer(final CardArea cardArea) {
        super(DEALER_NAME, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.calculate() <= 16;
    }

    public Card firstCard() {
        return cardArea.cards().get(0);
    }
}
