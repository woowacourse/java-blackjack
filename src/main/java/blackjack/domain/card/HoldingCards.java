package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class HoldingCards {

    private static final int MAXIMUM_SUM = 21;
    private static final int ACE_BONUS = 10;

    private final List<Card> cards = new ArrayList<>();

    public void initialize(Card firstCard, Card secondCard) {
        cards.addAll(List.of(firstCard, secondCard));
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getSum() {
        int aceCount = getAceCount();
        int pointSum = getNotAceSum() + aceCount;
        while (pointSum + ACE_BONUS <= MAXIMUM_SUM && aceCount > 0) {
            pointSum += ACE_BONUS;
            aceCount--;
        }
        return pointSum;
    }

    private int getNotAceSum() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getPoint)
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }
}
