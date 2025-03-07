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
            throw new IllegalArgumentException("딜러는 손패를 가져야합니다.");
        }
    }

    public TrumpCard retrieveFirstCard() {
        if (hand.getCards().size() != 2) {
            throw new IllegalStateException("딜러는 2장의 카드를 가지고 있어야 합니다.");
        }

        return hand.getCards().getFirst();
    }

    public Hand getHand() {
        return hand;
    }
}
