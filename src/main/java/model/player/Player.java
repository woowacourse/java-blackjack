package model.player;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class Player {

    private static final int ADD_CARD_CONDITION = 22;

    private final String name;
    private final Cards cards;

    public Player(String name) {
        this(name, new Cards(List.of()));
    }

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean isPossibleAddCard() {
        int totalNumbers = cards.calculateTotalNumbers();
        return totalNumbers < ADD_CARD_CONDITION;
    }

    public Player addCard(Card card) {
        if (isPossibleAddCard()) {
            Cards addedCards = cards.add(card);
            return new Player(name, addedCards);
        }
        return this;
    }

    public int cardSize() {
        return cards.size();
    }
}
