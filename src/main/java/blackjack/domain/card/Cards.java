package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final String NO_REMAIN_CARD_ERROR_MESSAGE = "남은 카드가 없습니다.";
    private final List<Card> cards;

    private static final List<Card> CACHE;

    static {
        CACHE = Arrays.stream(Denomination.values())
                .flatMap(denomination -> Arrays.stream(Shape.values())
                        .map(shape -> new Card(shape, denomination)))
                .collect(Collectors.toList());
    }

    public Cards() {
        this(Collections.emptyList());
    }

    public Cards(Card card) {
        this(Collections.singletonList(card));
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static List<Card> values() {
        return Collections.unmodifiableList(CACHE);
    }

    public Cards addCard(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Cards(newCards);
    }

    public Card peekCard() {
        return cards.get(0);
    }

    public Cards removeCard() {
        if (cards.size() == 0) {
            throw new IndexOutOfBoundsException(NO_REMAIN_CARD_ERROR_MESSAGE);
        }
        List<Card> newCards = new ArrayList<>(cards);
        newCards.remove(0);
        return new Cards(newCards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
