package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {
    private final Cards cards;

    public Deck() {
        this.cards = createDeck();
    }

    public Cards createDeck() {
        List<Card> cards = new ArrayList<>();

        for (Figure figure : Figure.values()) {
            cards.addAll(matchNumbersWith(figure));
        }
        return new Cards(cards);
    }
    
    private List<Card> matchNumbersWith(Figure figure) {
        return Arrays.stream(Number.values())
                .map(number -> new Card(figure, number))
                .toList();
    }

    public void shuffle() {
        cards.shuffle();
    }

    public Card hit() {
        return cards.giveFirstCard();
    }

    public int getDeckSize() {
        return cards.getSize();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
