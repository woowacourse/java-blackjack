package domain.player;

import domain.card.CardBundle;

public abstract class Gamer {

    protected final PlayerName name;
    protected CardBundle cardBundle;

    public Gamer(PlayerName name) {
        this.name = name;
        this.cardBundle = CardBundle.empty();
    }

    public int getResultScore() {
        return cardBundle.getResultScore();
    }

    public boolean isBusted() {
        return cardBundle.isBusted();
    }

}
