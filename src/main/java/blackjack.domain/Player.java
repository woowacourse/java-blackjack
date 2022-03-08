package blackjack.domain;

public abstract class Player {
    private static final int BURST_CRITERIA = 21;

    private final String name;
    private PlayingCards playingCards = new PlayingCards();

    protected Player(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addCard(PlayingCard playingCard) {
        playingCards.addCard(playingCard);
    }

    public int getResult() {
        return playingCards.getResult();
    }

    public boolean isBurst() {
        return getResult() > BURST_CRITERIA;
    }
}
