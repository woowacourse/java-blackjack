package domain;

import dto.GameStatus;

public class Player extends Participant {
    private static final int PLAYER_PLAYING_THRESHOLD = 20;

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= PLAYER_PLAYING_THRESHOLD;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public GameStatus status() {
        return new GameStatus(ParticipantsRole.PLAYER, this.name(), hand, scoreSum());
    }
}
