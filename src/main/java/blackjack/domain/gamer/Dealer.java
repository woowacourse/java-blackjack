package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer {

    private static final int HIT_THRESHOLD = 16;

    public Dealer() {}

    @Override
    public boolean canHit() {
        return hand.sum() <= HIT_THRESHOLD;
    }

    public Card findFaceUpCard() {
        return hand.findFirst();
    }
}
