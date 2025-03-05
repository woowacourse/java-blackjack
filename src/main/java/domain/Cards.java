package domain;

import static util.ExceptionConstants.ERROR_HEADER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int VALID_MAX_SUM_LIMIT = 21;
    private static final int VALID_DRAW_LIMIT = 16;
    private static final String MAX_SUM_EXCEED_ERROR = "카드의 합이 21을 초과하였습니다.";

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createEmpty() {
        return new Cards(new ArrayList<>());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void checkMaxSum() {
        if(calculateSum() > VALID_MAX_SUM_LIMIT) {
            throw new IllegalArgumentException(ERROR_HEADER + MAX_SUM_EXCEED_ERROR);
        }
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isUnderDrawLimit() {
        return calculateSum() <= VALID_DRAW_LIMIT;
    }

    public int calculateSum() {
        return cards.stream()
                .map(Card::cardNumberType)
                .mapToInt(CardNumberType::getValue)
                .sum();
    }
}
