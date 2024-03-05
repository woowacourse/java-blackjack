package blackjack.domain;

public enum BlackjackStatus {
    DEAD, ALIVE, BLACKJACK;

    public static final int BLACKJACK_NUMBER = 21;

    public static BlackjackStatus from(final int number) {
        if (number < 2) {
            throw new IllegalStateException("현재 갖고있는 카드의 합이 정상적이지 않습니다.");
        }
        if (number > BLACKJACK_NUMBER) {
            return DEAD;
        }
        if (number == BLACKJACK_NUMBER) {
            return BLACKJACK;
        }
        return ALIVE;
    }
}
