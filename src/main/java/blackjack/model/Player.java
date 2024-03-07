package blackjack.model;

import java.util.List;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public void addCards(List<Card> cardToAdd) {
        cards.addCard(cardToAdd);
    }

    public boolean checkDrawCardState() {
        return !cards.isGreaterThanWinningScore();
    }

    public Cards getCards() {
        return cards;
    }
}
