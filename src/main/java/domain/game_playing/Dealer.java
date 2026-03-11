package domain.game_playing;

class Dealer extends Participant {

    private static final String NAME = "딜러";
    private static final int DEALER_PLAYABLE_THRESHOLD = 16;

    Dealer(String name, Hand hand) {
        super(name, hand);
    }

    static Dealer of(DrawStrategy drawStrategy) {
        return new Dealer(NAME, Hand.based(drawStrategy));
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= DEALER_PLAYABLE_THRESHOLD;
    }
}
