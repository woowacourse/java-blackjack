package blackjack.domain.card;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private final Deque<Card> deck;

    public Deck(Deque<Card> deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 남은 카드가 없습니다.");
        }
        return deck.poll();
    }

    public List<Card> drawCards(int count) {
        if (deck.size() < count) {
            throw new IllegalStateException("[ERROR] 카드가 부족합니다.");
        }
        return IntStream.range(0, count)
                .mapToObj(v -> this.drawCard())
                .collect(Collectors.toList());
    }

    public Collection<Card> getCards() {
        return Collections.unmodifiableCollection(deck);
    }
}
