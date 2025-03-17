package domain;

import static util.ExceptionConstants.ERROR_HEADER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

    private static final String NO_ENOUGH_CARD = "카드가 충분하지 않습니다.";

    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ERROR_HEADER + NO_ENOUGH_CARD);
        }
        return cards.removeFirst();
    }

    public List<Card> drawCards(int count) {
        if (cards.size() < count) {
            throw new IllegalStateException(ERROR_HEADER + NO_ENOUGH_CARD);
        }
        return IntStream.range(0, count)
                .mapToObj(i -> cards.removeFirst())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Deck createShuffledBlackJackCards() {
        List<Card> cards = new ArrayList<>();
        for (CardNumberType cardNumberType : CardNumberType.values()) {
            for (CardType cardType : CardType.values()) {
                cards.add(new Card(cardNumberType, cardType));
            }
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
