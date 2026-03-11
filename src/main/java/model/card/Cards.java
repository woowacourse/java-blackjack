package model.card;

import static model.Blackjack.BLACKJACK_SCORE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<Card> values;

    private Cards(List<Card> values) {
        this.values = new ArrayList<>(values);
    }

    public static Cards from(List<Card> cards) {
        List<Card> copied = List.copyOf(cards);

        return new Cards(copied);
    }

    public void add(Card card) {
        Objects.requireNonNull(card);

        values.add(card);
    }

    public Card getFirst() {
        if (values.isEmpty()) {
            throw new IllegalStateException("가진 카드 패가 없어 오픈할 수 없습니다.");
        }

        return values.getFirst();
    }

    public int calculateScore() {
        int total = calculate();
        int aceCardCount = aceCount();

        while (aceCardCount-- > 0 && total > BLACKJACK_SCORE) {
            total -= 10;
        }

        return total;
    }

    private int calculate() {
        return values.stream()
                .map(Card::getRank)
                .mapToInt(Rank::toValue)
                .sum();
    }

    private int aceCount() {
        int count = 0;
        for (Card card : values) {
            if (card.getRank() == Rank.ACE) {
                count++;
            }
        }

        return count;
    }

    public List<Card> asList() {
        return List.copyOf(values);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "values=" + values +
                '}';
    }
}
