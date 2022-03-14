package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private final LinkedList<Card> deck;

    public Deck() {
        this.deck = createDeck();
        Collections.shuffle(this.deck);
    }

    private LinkedList<Card> createDeck() {
        return Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(shape, number)))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public Card draw() {
        validateEmpty();
        return deck.remove();
    }

    private void validateEmpty() {
        if (deck.isEmpty()) {
            throw new NoSuchElementException("카드를 모두 소진했습니다.");
        }
    }

    public int getSize() {
        return deck.size();
    }
}
