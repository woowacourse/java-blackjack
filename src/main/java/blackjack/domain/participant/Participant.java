package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_NUMBER = 1;
    private static final int ALTERNATE_ACE_VALUE = 10;

    protected String name;
    protected List<Card> cards = new ArrayList<>();

    public void drawCard(final Deck deck) {
        cards.add(deck.drawCard());
    }

    protected int calculateScore() {
        int totalSum = calculateWithoutAce();

        if (hasAceCard()) {
            totalSum = checkAceScore(totalSum);
        }
        return totalSum;
    }

    protected int calculateWithoutAce() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(card -> card.getNumber() == ACE_NUMBER);
    }

    private int checkAceScore(int totalSum) {
        if (totalSum + ALTERNATE_ACE_VALUE > BLACKJACK_NUMBER) {
            return totalSum;
        }
        return totalSum + ALTERNATE_ACE_VALUE;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_NUMBER;
    }

    public int getScore() {
        return calculateScore();
    }

    public List<Card> openAllCards() {
        return List.copyOf(cards);
    }

    public String getName() {
        return name;
    }

    public abstract List<Card> showFirstCards();
}
