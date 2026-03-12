package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {
    private final Cards cards;

    public Deck() {
        this.cards = createDeck();
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

    private Cards createDeck() {
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
}
