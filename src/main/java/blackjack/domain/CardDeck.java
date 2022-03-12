package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private final Deque<Card> cards;

    public CardDeck() {
        final List<Card> cards = generateAllCards();
        Collections.shuffle(cards);

        this.cards = new ArrayDeque<>(cards);
    }

    private List<Card> generateAllCards() {
        return Stream.of(Denomination.values())
                .flatMap(denomination ->
                        Stream.of(Suit.values())
                                .map(suit -> new Card(denomination, suit)))
                .collect(Collectors.toList());
    }

    public Card pickCard() {
        try {
            return cards.pop();
        } catch (NoSuchElementException exception) {
            throw new NoSuchElementException("[ERROR] 카드를 더 이상 뽑을 수 없습니다.");
        }
    }
}
