package domain.card;

import domain.GameManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_VALUE_DIFFERENCE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateTotalCardNumber() {
        int total = cards.stream()
                .mapToInt(card -> card.getNumber().getNumericValue())
                .sum();

        int aceCount = countAces();
        while (total > BLACKJACK_SCORE && aceCount > 0) {
            total -= ACE_VALUE_DIFFERENCE;
            aceCount--;
        }

        return total;
    }

    private int countAces() {
        return (int) cards.stream()
            .filter(Card::isAce)
            .count();
    }

    public void addCards(List<Card> providedCards) {
        cards.addAll(providedCards);
    }

    public boolean isBurst() {
        return calculateTotalCardNumber() > BLACKJACK_SCORE;
    }

    public int calculateDifferenceFromBurst() {
        return Math.abs(calculateTotalCardNumber() - BLACKJACK_SCORE);
    }

    public Card getInitialCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBlackJack() {
        return cards.size() == GameManager.INITIAL_DRAW_SIZE
            && calculateTotalCardNumber() == BLACKJACK_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
