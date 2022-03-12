package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private static final String DECK_EMPTY_ERROR = "[ERROR] 카드를 모두 사용하였습니다.";

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            Stream.of(Denomination.values())
                .forEach(denomination -> cards.add(new Card(suit, denomination)));
        }
        Collections.shuffle(cards);
    }

    public Card pickCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(DECK_EMPTY_ERROR);
        }
        return cards.remove(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
