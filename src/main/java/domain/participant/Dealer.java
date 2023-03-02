package domain.participant;

import domain.area.CardArea;

public class Dealer extends Player {

    public Dealer(final Name name, final CardArea cardArea) {
        super(name, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.calculate() <= 16;
    }
}
