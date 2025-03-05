package model;

import java.util.Set;

public class Cards {

    private final Set<Card> cards;

    public Cards(final Set<Card> cards) {
        this.cards = cards;
    }

    public int calculateSum() {
        int aceCount = findAceElevenCount();

        while (isBurst() && aceCount > 0) {
            aceCount--;
            changeAceElevenToOne();
        }

        return cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
    }

    private boolean isBurst() {
        int sum = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
        return sum > 21;
    }

    private int findAceElevenCount() {
        return (int) cards.stream()
                .filter(card -> card.isSameNumber(CardNumber.ACE_ELEVEN))
                .count();
    }

    private void changeAceElevenToOne() {
        final Card aceElevenCard = cards.stream()
                .filter(card -> card.isSameNumber(CardNumber.ACE_ELEVEN))
                .findAny()
                .orElseThrow();

        final CardShape cardShape = aceElevenCard.getShape();

        cards.remove(aceElevenCard);
        cards.add(new Card(CardNumber.ACE_ONE, cardShape));
    }
}
