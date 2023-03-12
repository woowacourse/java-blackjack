package domain.deck;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BLACK_JACK = 21;
    private static final int DECREASE_ACE_VALUE = -10;
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getSum() {
        int sum = cards.stream()
                .map(Card::getCardValue)
                .mapToInt(Integer::intValue)
                .sum();
        return calculateAceValue(sum);
    }

    private int calculateAceValue(int sum) {
        while (aceExist() && sum > BLACK_JACK) {
            sum += DECREASE_ACE_VALUE;
        }
        return sum;
    }

    private boolean aceExist() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }
}
