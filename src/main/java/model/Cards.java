package model;

import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BUST_THRESHOLD = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateResult() {
        changeAceElevenToOneUntilNotBust();
        return calculateSum();
    }

    private void changeAceElevenToOneUntilNotBust() {
        int aceCount = findAceElevenCount();
        while (isBust() && aceCount > 0) {
            aceCount--;
            changeAceElevenToOne();
        }
    }

    private int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
    }

    public void addCard(final Card card) {
        if (isBust()) {
            throw new IllegalArgumentException();
        }
        cards.add(card);
    }

    public boolean isBust() {
        int sum = calculateSum();
        return sum > BUST_THRESHOLD;
    }

    private int findAceElevenCount() {
        return (int) cards.stream()
                .filter(card -> card.isSameNumber(CardNumber.ACE_ELEVEN))
                .count();
    }

    private void changeAceElevenToOne() {
        Card aceElevenCard = cards.stream()
                .filter(card -> card.isSameNumber(CardNumber.ACE_ELEVEN))
                .findAny()
                .orElseThrow();

        CardShape cardShape = aceElevenCard.getShape();

        cards.remove(aceElevenCard);
        cards.add(new Card(CardNumber.ACE_ONE, cardShape));
    }

    public int getAdditionalDrawCount() {
        return cards.size() - 2;
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
