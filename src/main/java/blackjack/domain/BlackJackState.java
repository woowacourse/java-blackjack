package blackjack.domain;

public enum BlackJackState {
    BLACKJACK(),
    BUST(),
    OTHERS();

    public static BlackJackState of(Participant participant) {
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
