package blackjack.gamer;

import blackjack.domain.card.Cards;

public class Dealer extends GameParticipant {

    private static final int DEALER_HIT_THRESHOLD_POINT = 16;

    private Dealer(Cards cards) {
        super(cards);
    }

    public static Dealer create() {
        return new Dealer(Cards.empty());
    }

    @Override
    public boolean shouldHit() {
        return calculateSumOfCards() <= DEALER_HIT_THRESHOLD_POINT;
    }
}
