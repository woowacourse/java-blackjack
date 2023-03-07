package domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardArea {

    private static final int SPECIAL_ACE_ADD_VALUE = 10;
    private static final int BLACK_JACK_NUMBER = 21;
    private static final int FIRST_CARD_INDEX = 0;

    private final List<Card> cards = new ArrayList<>();

    public CardArea(final Card firstCard, final Card secondCard) {
        cards.addAll(List.of(firstCard, secondCard));
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int calculate() {
        int total = cards.stream().mapToInt(Card::defaultScore).sum();
        if (hasAce()) {
            return calculateSpecialAceValue(total);
        }
        return total;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateSpecialAceValue(final int total) {
        if (total + SPECIAL_ACE_ADD_VALUE <= BLACK_JACK_NUMBER) {
            return total + SPECIAL_ACE_ADD_VALUE;
        }
        return total;
    }

    public boolean canMoreCard() {
        return calculate() < BLACK_JACK_NUMBER;
    }

    public boolean isBurst() {
        return calculate() > BLACK_JACK_NUMBER;
    }

    public Card firstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }
}
