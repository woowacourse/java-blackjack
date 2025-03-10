package domain;

public class Dealer extends Participant {

    public static final int INITIAL_CARD_COUNT = 2;

    public Dealer(Hand hand) {
        super(hand);
    }

    public TrumpCard retrieveFirstCard() {
        if (hand.getCards().size() != INITIAL_CARD_COUNT) {
            throw new IllegalStateException("딜러는 " + INITIAL_CARD_COUNT + "장의 카드를 가지고 있어야 합니다.");
        }

        return hand.getCards().getFirst();
    }
}
