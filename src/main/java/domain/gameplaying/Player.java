package domain.gameplaying;

class Player extends Participant {

    private static final int PLAYER_PLAYABLE_THRESHOLD = 20;

    Player(String name, Hand hand) {
        super(name, hand);
        requireNonDealer(name);
    }

    static Player of(String name, BlackJackDeck deck) {
        return new Player(name, Hand.with(deck));
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= PLAYER_PLAYABLE_THRESHOLD;
    }

    private void requireNonDealer(String name) {
        if (name.equals(DEALER_NAME)) {
            String errorMessage = String.format("플레이어는 \"%s\"라는 이름을 사용할 수 없다.", DEALER_NAME);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
