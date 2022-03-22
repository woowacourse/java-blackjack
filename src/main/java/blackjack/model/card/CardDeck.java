package blackjack.model.card;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public final class CardDeck {

    private final Queue<Card> deck;

    public CardDeck() {
        this.deck = new LinkedList<>(shuffledCards());
    }

    private List<Card> shuffledCards() {
        List<Card> cardPool = createCardPool();
        Collections.shuffle(cardPool);
        return cardPool;
    }

    private List<Card> createCardPool() {
        List<Card> pool = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            pool.addAll(createCardsEach(suit));
        }
        return pool;
    }

    private List<Card> createCardsEach(Suit suit) {
        return Stream.of(Rank.values())
            .map(rank -> new Card(rank, suit))
            .collect(toUnmodifiableList());
    }

    public Card next() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("남아있는 카드가 없습니다.");
        }
        return deck.remove();
    }
}
