package area;

import card.Card;

public class DealerCardArea extends CardArea {

    public DealerCardArea(final Card firstCard, final Card secondCard) {
        super(firstCard, secondCard);
    }

    @Override
    public boolean wantHit() {
        return calculate() <= 17;
    }
}
