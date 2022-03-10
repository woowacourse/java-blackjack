package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private static final String NO_CARD_ERROR_MESSAGE = "더 이상 뽑을 수 있는 카드가 없습니다.";

    private final List<Card> cards = new LinkedList<>();

    public Deck() {
        init();
    }

    public void init() {
        cards.clear();
        Arrays.stream(Suit.values()).forEach(this::addAllDenomination);
        Collections.shuffle(cards);
    }

    private void addAllDenomination(Suit suit) {
        Arrays.stream(Denomination.values())
                .forEach(denomination -> cards.add(new Card(denomination, suit)));
    }

    public Card draw() {
        try {
            return cards.remove(cards.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException(NO_CARD_ERROR_MESSAGE);
        }
    }
}
