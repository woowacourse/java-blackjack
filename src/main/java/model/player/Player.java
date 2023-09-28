package model.player;

import model.card.Card;
import model.cards.Cards;
import model.name.Name;

import java.util.List;

public class Player {

    private final Name name;
    private final Cards cards;

    private Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Player of(final Name name, final Cards cards) {
        return new Player(name, cards);
    }

    public void selectCardsFromDeck(List<Card> card) {
        cards.addCards(card);
    }

    public Name getName() {
        return name;
    }

    public int getScore() {
        return cards.calculateScore();
    }
}
