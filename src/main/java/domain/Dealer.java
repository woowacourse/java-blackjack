package domain;

import dto.GameStatus;

public class Dealer extends Participant {
    private static final int DEALER_PLAYING_THRESHOLD = 16;

    public Dealer(String name, Hand hand) {
        super("딜러", hand);
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= DEALER_PLAYING_THRESHOLD;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public GameStatus status() {
        return new GameStatus(ParticipantsRole.DEALER, this.name(), hand);
    }
}
