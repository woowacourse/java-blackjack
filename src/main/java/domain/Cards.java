package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int MAX_SCORE = 21;
    private static final int INIT_CARD_SIZE = 2;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<Card> cards) {
        if (cards.size() != INIT_CARD_SIZE) {
            throw new IllegalArgumentException("처음 지급받는 카드는 두 장이어야 합니다.");
        }
    }

    public boolean isOverMaxScore() {
        int sum = cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
        return sum > MAX_SCORE;
    }

    public List<Card> add(Card card) {
        cards.add(card);
        return List.copyOf(cards);
    }
}
