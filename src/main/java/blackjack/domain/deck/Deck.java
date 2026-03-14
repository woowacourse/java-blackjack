package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Number;

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

    private Cards createDeck() {
        List<Card> cards = Arrays.stream(Figure.values())
                .flatMap(figure -> Arrays.stream(Number.values())
                        .map(number -> new Card(figure, number)))
                .toList();
        return new Cards(cards);
    }
}
