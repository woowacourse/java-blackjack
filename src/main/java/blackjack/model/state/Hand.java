package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardValue;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int SOFT_ACE_VALUE = 11;
    private static final int SOFT_HAND_AVAILABLE_THRESHOLD = 10;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    private Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand add(Card card) {
        cards.add(card);
        return new Hand(new ArrayList<>(cards));
    }

    public int size() {
        return cards.size();
    }

    public int getTotal() {
        int total = sumHandWithoutAce();
        if (hasAce()) {
            return total + calculateAceValue(total);
        }
        return total;
    }

    private int sumHandWithoutAce() {
        List<Card> handWithoutAce = getHandWithoutAce();
        int total = 0;
        for (Card card : handWithoutAce) {
            total += card.getCardValue().getDefaultValue();
        }
        return total;
    }

    private List<Card> getHandWithoutAce() {
        return cards.stream()
                .filter(card -> card.getCardValue() != CardValue.ACE)
                .toList();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getCardValue() == CardValue.ACE);
    }

    private int calculateAceValue(int total) {
        int aceCount = getAceCountInHand();
        int aceValue = 0;
        if (total <= SOFT_HAND_AVAILABLE_THRESHOLD) {
            aceValue += SOFT_ACE_VALUE;
            aceCount--;
        }
        return aceValue + (aceCount * CardValue.ACE.getDefaultValue());
    }

    private int getAceCountInHand() {
        return (int) cards.stream()
                .filter(card -> card.getCardValue() == CardValue.ACE)
                .count();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
