package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_POINT_DIFFERENCE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> getLimitedCard(int size) {
        return Collections.unmodifiableList(cards.subList(0, size));
    }

    public int getScore() {
        if (containsAce(cards)) {
            return sumWithAce();
        }

        return getSumPoint();
    }

    private boolean containsAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(card -> card.isAce());
    }

    private int sumWithAce() {
        int sum = getSumPoint();

        if (sum + ACE_POINT_DIFFERENCE <= BLACKJACK_NUMBER) {
            sum = sum + ACE_POINT_DIFFERENCE;
        }
        
        return sum;
    }

    public int getSumPoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }
}
