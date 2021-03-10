package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int ACE_EXTRA_VALUE = 10;
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getUnmodifiableList() {
        return Collections.unmodifiableList(cards);
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public Score sumCards() {
        final int sum = cards.stream()
            .mapToInt(Card::getScore)
            .sum();
        return Score.of(sum);
    }

    public Score sumCardsForResult() {
        int aceCount = (int) cards.stream()
            .filter(Card::isAce)
            .count();
        Score sum = sumCards().add(aceCount * ACE_EXTRA_VALUE);
        while (sum.isBurst() && aceCount > 0) {
            sum = sum.subtract(ACE_EXTRA_VALUE);
            aceCount--;
        }
        return sum;
    }

}