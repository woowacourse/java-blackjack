package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private static final List<Card> cards;

    static {
        cards = Stream.of(Denomination.values())
                .flatMap(denomination ->
                        Stream.of(Suit.values())
                                .map(suit -> new Card(denomination, suit)))
                .collect(Collectors.toList());
    }

    public CardDeck() {
        Collections.shuffle(cards);
    }

    public Card getCard(final int index) {
        checkIndex(index);
        return cards.get(index);
    }

    private void checkIndex(int index) {
        if (index >= cards.size()) {
            throw new IndexOutOfBoundsException("[ERROR] 카드 덱의 범위를 넘어갔습니다.");
        }
    }
}
