package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private static final String EMPTY_DECK_MESSAGE = "덱에 남은 카드가 없습니다.";

    private final List<Card> cards;

    public Deck() {
        this.cards = createAllCards();
        Collections.shuffle(this.cards);
    }

    private List<Card> createAllCards() {
        return Arrays.stream(Suit.values())
                        .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(suit, rank)))
                        .collect(Collectors.toList());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK_MESSAGE);
        }
        return cards.removeFirst();
    }

    public int size() {
        return cards.size();
    }
}
