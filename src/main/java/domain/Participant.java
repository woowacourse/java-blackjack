package domain;

import dto.GameStatus;

public abstract class Participant {

    private final String name;
    protected final Hand hand;

    public Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    private static Participant player(String name) {
        return Player.of(name);
    }

    protected abstract boolean isPlayable();

    public void draw(DrawStrategy drawStrategy) {
        if (isPlayable()) {
            hand.drawCard(drawStrategy);
        }
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public int scoreSum() {
        return hand.scoreSum();
    }

    public String name() {
        return name;
    }

    public GameStatus status() {
        return new GameStatus(name, hand.cardInfos(), scoreSum());
    }

    public boolean isPlayer() {
        return this.getClass() == Player.class;
    }
}
