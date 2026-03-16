package domain.gameplaying;

class Dealer extends Participant {

    private static final int DEALER_PLAYABLE_THRESHOLD = 16;

    Dealer(String name, Hand hand) {
        super(name, hand);
    }

    static Dealer of(BlackJackDeck blackJackDeck) {
        return new Dealer(DEALER_NAME, Hand.with(blackJackDeck));
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= DEALER_PLAYABLE_THRESHOLD;
    }
}
