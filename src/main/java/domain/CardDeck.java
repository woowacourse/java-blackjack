package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>(Card.values());
        shuffleCards();
    }

    CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public void initCard() {
        this.cards = Card.values();
        shuffleCards();
    }

    private void shuffleCards() {
        Collections.shuffle(this.cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card pick() {
        return cards.remove(0);
    }

    public List<Card> pickTwice() {
        List<Card> pick = new ArrayList<>();
        pick.add(pick());
        pick.add(pick());
        return pick;
    }
}
