package domain;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    @Override
    public void shouldReceive(Deck deck) {
        int score = super.score();

        if (score <= 16) {
            super.receive(deck);
        }
    }
}
