package blackjack.domain.participant;

import blackjack.domain.MatchResultType;
import blackjack.domain.card.Card;


public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_THRESHOLD = 16;

    public boolean canHit() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    public Card getFirstCard() {
        return this.cards.getFirstCard();
    }

    public MatchResultType compare(Player player) {
        return MatchResultType.getStatus(this.cards.calculateScore(), player.calculateScore());
    }

    @Override
    public String getCards() {
        return this.cards.getCards();
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public int calculateScore() {
        return cards.calculateScore();
    }

    @Override
    public String showCardsAtFirst() {
        return getFirstCard().getName();
    }
}
