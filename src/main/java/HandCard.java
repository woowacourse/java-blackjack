import card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HandCard {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACKJACK_FULL_SCORE = 21;

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
        if (sum <= (BLACKJACK_FULL_SCORE - ACE_BONUS_SCORE) && hasAce()) {
            return sum + ACE_BONUS_SCORE;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .map(Card::isAce)
                .findAny()
                .orElse(false);
    }
}
