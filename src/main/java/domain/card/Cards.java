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

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateScore() {
        int sum = 0;
        int aceCount = 0;

        for (Card card : cards) {
            List<Integer> scores = card.getScore();
            sum += scores.get(0);
            if (scores.size() > 1) {
                aceCount++;
            }
        }
        while (aceCount > 0 && sum + 10 <= 21) {
            sum += 10;
            aceCount--;
        }

        return sum;
    }
}
