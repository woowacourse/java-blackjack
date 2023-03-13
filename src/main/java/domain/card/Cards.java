package domain.card;

import java.util.List;

public class Cards {

    public static final int BLACKJACK_NUMBER = 21;
    public static final int NUMBER_OF_INITIAL_CARDS = 2;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateInitialSum() {
        int initialSum = addInitialCardValues();
        if (hasAce()) {
            return selectAceValue(initialSum);
        }
        return initialSum;
    }


    public int calculateSum() {
        int sum = addAllCardValues();
        if (hasAce()) {
            return selectAceValue(sum);
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .map(Card::getCardValue)
                .anyMatch(value -> value == CardValue.ACE);
    }

    private int addInitialCardValues() {
        int initialSum = 0;
        for (int cardIndex = 0; cardIndex < NUMBER_OF_INITIAL_CARDS; cardIndex++) {
            initialSum += cards.get(cardIndex).getCardValue().getCardValue();
        }
        return initialSum;
    }

    private int addAllCardValues() {
        return cards.stream()
                .map(Card::getCardValue)
                .mapToInt(CardValue::getCardValue)
                .sum();
    }

    private int selectAceValue(int sum) {
        if (sum > 11) {
            return sum;
        }
        return sum + 10;
    }

    public boolean isBlackjack() {
        return calculateSum() == BLACKJACK_NUMBER;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getSize() {
        return cards.size();
    }
}
