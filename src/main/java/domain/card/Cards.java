package domain.card;

import java.util.List;

public class Cards {
    private final List<Card> cards;
    private final static int MIN_SIZE = 1;
    static final String INVALID_SIZE_MESSAGE = String.format("카드 갯수가 최소 갯수인 %d보다 작습니다", MIN_SIZE);

    Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(List<Card> cards) {
        if (cards.size() < MIN_SIZE) {
            throw new IllegalArgumentException(INVALID_SIZE_MESSAGE);
        }
        return new Cards(cards);
    }

    protected List<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }
}
