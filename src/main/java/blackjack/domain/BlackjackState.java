package blackjack.domain;

public enum BlackjackState {
    BLACKJACK(),
    BUST(),
    OTHERS();

    public static BlackjackState of(Participant participant) {
        int cardSum = participant.calculateDenominations();
        if (participant.hasTwoCards() && cardSum == 21) {
            return BLACKJACK;
        }
        if (cardSum > 21) {
            return BUST;
        }
        return OTHERS;
    }
}
