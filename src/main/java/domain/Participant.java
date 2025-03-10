package domain;

public abstract class Participant {

    protected final Hand hand;

    protected Participant(Hand hand) {
        validate(hand);
        this.hand = hand;
    }

    private void validate(Hand hand) {
        validateNotNull(hand);
    }

    private void validateNotNull(Hand hand) {
        if (hand == null) {
            throw new IllegalArgumentException("참가자는 손패를 가져야합니다.");
        }
    }

    public Hand getHand() {
        return hand;
    }
}
