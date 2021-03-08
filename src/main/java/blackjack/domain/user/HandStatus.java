package blackjack.domain.user;

public enum HandStatus {
    HIT, STAY, BUST, BLACK_JACK;

    public static final int MAX_SCORE = 21;
    public static final int BLACK_JACK_CARD_SIZE = 2;

    public static HandStatus calculateStatus(int score, int hitLimit, int cardSize) {
        if (score == MAX_SCORE && cardSize == BLACK_JACK_CARD_SIZE) {
            return BLACK_JACK;
        }
        if (score > MAX_SCORE) {
            return BUST;
        }
        if (score > hitLimit) {
            return STAY;
        }
        return HIT;
    }
}
