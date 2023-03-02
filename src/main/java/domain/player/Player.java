package domain.player;

import domain.card.Card;
import domain.card.Cards;

public abstract class Player {
    private final Cards cards;

    public Player() {
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int getTotalScore() {
        return cards.getTotalScore();
    }

    public boolean isBurst() {
        return cards.isBurst();
    }

    public abstract void battle(Player player);

    public abstract int getWinningCount();

    public abstract int getLoseCount();

    public abstract int getDrawCount();
}
