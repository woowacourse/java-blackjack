package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private final Deque<Card> deck;

    public CardDeck() {
        List<Card> deck = getAllCards();
        Collections.shuffle(deck);
        this.deck = new ArrayDeque<>(deck);
    }

    private List<Card> getAllCards() {
        return Stream.of(Denomination.values())
                .flatMap(denomination ->
                        Stream.of(Suit.values())
                                .map(suit -> new Card(denomination, suit)))
                .collect(Collectors.toList());
    }

    public Card getCard() {
        try {
            return deck.pop();
        } catch (NoSuchElementException exception) {
            throw new NoSuchElementException("[ERROR] 카드가 존재하지 않습니다.");
        }
    }
}
