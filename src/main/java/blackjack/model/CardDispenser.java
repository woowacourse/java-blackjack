package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDispenser {

    private static final List<Card> CARD_POOL = createCardPool();

    private final List<Card> deck;
    private int index;

    public CardDispenser() {
        this.deck = shuffledCards();
        this.index = 0;
    }

    private List<Card> shuffledCards() {
        List<Card> cards = new ArrayList<>(CARD_POOL);
        Collections.shuffle(cards);
        return cards;
    }

    public Card issue() {
        if (index >= deck.size()) {
            throw new IllegalStateException("남아있는 카드가 없습니다.");
        }
        return deck.get(index++);
    }

    private static List<Card> createCardPool() {
        return Stream.of(Suit.values())
            .flatMap(CardDispenser::eachSuitCards)
            .collect(Collectors.toUnmodifiableList());
    }

    private static Stream<Card> eachSuitCards(Suit suit) {
        return Stream.of(Rank.values())
            .map(rank -> new Card(rank, suit));
    }
}
