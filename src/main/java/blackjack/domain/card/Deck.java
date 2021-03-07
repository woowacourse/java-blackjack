package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        cards = init();
    }

    private Deque<Card> init() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Suit.values())
            .forEach(suit -> matchDenomination(cards, suit));
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    private void matchDenomination(List<Card> cards, Suit suit) {
        Arrays.stream(Denomination.values())
            .forEach(denomination -> cards.add(new Card(denomination, suit)));
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
