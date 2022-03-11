package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


public class Deck {

    private final static int FIRST_INDEX = 0;

    private final static String RUN_OUT_OF_CARDS_ERROR_MESSAGE = "[ERROR] 카드를 모두 사용하였습니다.";

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            Stream.of(Denomination.values())
                .forEach(denomination -> cards.add(new Card(suit, denomination)));
        }
        Collections.shuffle(cards);
    }

    public Card pickCard() {
        if (cards.size() == FIRST_INDEX) {
            throw new IllegalArgumentException(RUN_OUT_OF_CARDS_ERROR_MESSAGE);
        }
        return cards.remove(FIRST_INDEX);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
