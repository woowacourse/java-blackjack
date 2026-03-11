package domain;

public class Dealer extends Participant {
    private static final int MINIMUM_TOTAL_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

//    @Override
//    public Optional<Card> addCard(Deck totalDeck) {
//        if (super.calculateHandSum() <= MINIMUM_TOTAL_SCORE) {
//            return super.addCard(totalDeck);
//        }
//        return Optional.empty();
//    }
}
