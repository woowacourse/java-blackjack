package domain;

import dto.GameStatus;

public abstract class Participant {

    private final String name;
    protected final Hand hand;

    public Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    protected abstract boolean isPlayable();

    public String name() {
        return name;
    }

    public void draw() {
        if(isPlayable()) {
            hand.drawCard();
        }
    }

    public int scoreSum() {
        return hand.scoreSum();
    }

    public GameStatus status() {
        return new GameStatus(name, hand.cardInfos(), scoreSum());
    }

    public boolean isPlayer() {
        return this.getClass() == Player.class;
    }
}
