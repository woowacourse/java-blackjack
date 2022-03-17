package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomDeck implements Deck {

    private static final String NOT_REMAIN_CARDS_ERROR_MESSAGE = "모든 카드를 사용했습니다.";

    private final List<Card> cards = new ArrayList<>();

    public RandomDeck() {
        init();
    }

    @Override
    public Card pick() {
        validateRemainCards();

        return cards.remove(cards.size() - 1);
    }

    private void validateRemainCards() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(NOT_REMAIN_CARDS_ERROR_MESSAGE);
        }
    }

    private void init() {
        cards.clear();
        for (CardNumber number : CardNumber.values()) {
            Arrays.stream(Type.values())
                    .forEach(type -> cards.add(Card.of(number, type)));
        }
        Collections.shuffle(cards);
    }
}
