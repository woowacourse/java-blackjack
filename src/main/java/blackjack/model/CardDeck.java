package blackjack.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private final List<Card> deck;

    public CardDeck() {
        deck = Stream.of(Suit.values())
                .flatMap(this::cardGenerateStream)
                .collect(Collectors.toList());
        Collections.shuffle(deck);
    }

    private Stream<Card> cardGenerateStream(Suit suit) {
        return Stream.of(Rank.values())
                .map(rank -> new Card(rank, suit));
    }

    public Card selectCard() {
        if (deck.size() <= 0) {
            throw new IllegalStateException("남아있는 카드가 없습니다.");
        }
        return deck.remove(deck.size() - 1);
    }
}
