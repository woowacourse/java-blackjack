package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards; // 52장을 가지고 있음

    public Deck() {
        this.cards = createDeck();
    }

    public List<Card> createDeck() {
        List<Card> cards = new ArrayList<>();

        for (Figure figure : Figure.values()) {
            cards.addAll(matchNumbersWith(figure));
        }
        return cards;
    }

    private List<Card> matchNumbersWith(Figure figure) {
        return Arrays.stream(Number.values())
                .map(number -> new Card(figure, number))
                .toList();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card giveCard() {
        return cards.removeFirst();
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
