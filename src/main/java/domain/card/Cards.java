package domain.card;

import static domain.Hand.BLACKJACK_SCORE;

import java.util.ArrayList;
import java.util.List;

public record Cards(List<Card> cards) {

    public Cards() {
        this(List.of());
    }

    public Cards(List<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public Cards addCard(Card card) {
        List<Card> cards = new ArrayList<>(this.cards);
        cards.add(card);
        return new Cards(cards);
    }

    public int getSum() {
        int sum = getInitSum();
        long aceCount = countAce();

        while (aceCount > 0 && sum > BLACKJACK_SCORE) {
            sum -= 10;
            aceCount--;
        }

        return sum;
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public int size() {
        return cards.size();
    }

    private int getInitSum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private long countAce() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

}
