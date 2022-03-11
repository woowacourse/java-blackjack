package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Deck {

    private static final List<Card> CACHE_DECK = new ArrayList<>();
    private static final String EMPTY_CARD_EXCEPTION_MESSAGE = "[ERROR] 52장의 카드가 모두 소진되었습니다.";

    static {
        CACHE_DECK.addAll(Arrays.stream(Suit.values())
            .flatMap(suit -> Arrays.stream(Denomination.values())
                .map(denomination -> new Card(denomination, suit)))
            .collect(Collectors.toList()));
    }

    private final LinkedList<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck createShuffledCards() {
        Collections.shuffle(CACHE_DECK);
        return new Deck(CACHE_DECK);
    }

    public Card draw() {
        try {
            return cards.pop();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(EMPTY_CARD_EXCEPTION_MESSAGE);
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
