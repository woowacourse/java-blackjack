package blackjack.model.blackjack_player;

import blackjack.model.card.BlackJackCards;
import java.util.Collections;

public class Hand {

    private static final int BLACKJACK_POINT = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final BlackJackCards cards;

    private Hand(BlackJackCards cards) {
        this.cards = cards;
    }

    public static Hand empty() {
        return new Hand(BlackJackCards.empty());
    }

    public void addCards(final BlackJackCards cards) {
        this.cards.addAll(cards);
    }

    public final int calculateOptimalPoint() {
        return cards.calculatePossiblePoints().stream()
                .filter(point -> point <= BLACKJACK_POINT)
                .max(Integer::compareTo)
                .orElse(getMinimumPoint());
    }

    public final int getMinimumPoint() {
        return Collections.min(cards.calculatePossiblePoints());
    }

    public final boolean isBust() {
        return calculateOptimalPoint() > BLACKJACK_POINT;
    }

    public boolean isBlackjack() {
        return calculateOptimalPoint() == BLACKJACK_POINT
                && cards.hasSize(BLACKJACK_CARD_SIZE);
    }

    public final BlackJackCards getCards() {
        return cards;
    }
}
