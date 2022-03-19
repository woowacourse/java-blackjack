package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BONUS_SCORE = 10;
    private List<Card> cards;

    private Cards() {
        cards = new ArrayList<>();
    }

    public static Cards generateEmpty() {
        return new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int scoreSum() {
        int sum = cards.stream()
                .mapToInt(Card::score)
                .sum();
        return optimizeSum(sum);
    }

    public int numberOfCards() {
        return cards.size();
    }

    public Card pick(int index) {
        return cards.get(index);
    }

    private int optimizeSum(int sum) {
        if (hasAce()) {
            return sum + BONUS_SCORE;
        }
        return sum;
    }

    private boolean hasAce() {
        boolean containAce = false;
        for (Card card : cards) {
            containAce |= card.isAce();
        }
        return containAce;
    }
}
