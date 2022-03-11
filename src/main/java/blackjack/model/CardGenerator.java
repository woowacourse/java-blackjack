package blackjack.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardGenerator {

    private final List<Card> deck;
    private int index;

    public CardGenerator() {
        index = 0;
        deck = Stream.of(Suit.values())
                .flatMap(this::cardStream)
                .collect(Collectors.toList());
        Collections.shuffle(deck);
    }

    private Stream<Card> cardStream(Suit suit) {
        return Stream.of(Rank.values())
                .map(rank -> new Card(rank, suit));
    }

    public Card generate() {
        if (index >= deck.size()) {
            throw new IllegalStateException("남아있는 카드가 없습니다.");
        }

        return deck.get(index++);
    }
}
