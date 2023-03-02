package domain.player;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

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

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract void battle(Player player);

    public abstract List<Integer> getGameResult();

}
