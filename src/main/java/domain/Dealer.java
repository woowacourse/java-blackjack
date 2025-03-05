package domain;

public class Dealer {

    private final Hand hand;

    public Dealer(Hand hand) {
        validate(hand);
        this.hand = hand;
    }

    private void validate(Hand hand) {
        validateNotNull(hand);
    }

    private void validateNotNull(Hand hand) {
        if (hand == null) {
            throw new IllegalStateException("딜러는 손패를 가져야합니다.");
        }
    }

    public void receiveCard(TrumpCard card) {
        hand.addCard(card);
    }
}
