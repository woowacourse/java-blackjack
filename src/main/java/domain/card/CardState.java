package domain.card;

public enum CardState {
    BLACKJACK,
    STAND,
    BUST,
    ;

    private static final int BLACK_JACK_CARD_SCORE = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;

    public static CardState from(final Cards cards) {
        if (cards.getCardsCount() == BLACK_JACK_CARD_COUNT && cards.calculateSum() == BLACK_JACK_CARD_SCORE) {
            return BLACKJACK;
        }
        if (cards.calculateSum() > BLACK_JACK_CARD_SCORE) {
            return BUST;
        }
        return STAND;
    }

    public boolean isStand() {
        return this == STAND;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isBlackJack() {
        return this == BLACKJACK;
    }
}
