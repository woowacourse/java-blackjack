package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards() {
        this.cards = new ArrayList<>();
    }

    public static Cards of() {
        return new Cards();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(card -> card.getScore().getFirst())
                .sum();
        long aceCount = cards.stream()
                .filter(card -> card.getScore().size() > 1)
                .count();
        while (aceCount > 0 && sum + 10 <= 21) {
            sum += 10;
            aceCount--;
        }
        return sum;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
