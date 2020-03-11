import card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HandCard {
    private final List<Card> cards;

    public HandCard() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public String getNames() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(","));
    }

    public int getScore() {
        int sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        return getAceScore(sum);
    }

    private int getAceScore(int sum) {
        if (haveAce() && sum <= 11) {
            return sum + 10;
        }
        return sum;
    }

    private boolean haveAce() {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }
}
