package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int BLACK_JACK_NUMBER = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        Objects.requireNonNull(cards, "[Error] 카드에는 null이 들어올 수 없습니다.");
        this.cards = cards;
    }

    public int calculateScore() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getDefaultValue)
                .sum();
    }

    public void addCard(final Card card) {
        validateScoreSize();
        cards.add(card);
    }

    private void validateScoreSize() {
        if (calculateScore() > BLACK_JACK_NUMBER) {
            throw new IllegalStateException("[Error] 21이 넘을때는 카드를 더 추가할 수 없습니다.");
        }
    }

    public boolean isBust() {
        return calculateScore() > BLACK_JACK_NUMBER;
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }
}
