package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final Deque<Card> cards;

    public Deck(Deque<Card> shuffledCards) {
        cards = new ArrayDeque<>(shuffledCards);
    }

    public List<Card> pickInitialCards() {
        return Stream.generate(cards::pop)
            .limit(2)
            .collect(Collectors.toList());
    }

    public Card pickSingleCard() {
        return cards.pop();
    }
}
