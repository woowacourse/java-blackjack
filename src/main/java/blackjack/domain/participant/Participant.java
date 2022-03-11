package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

public class Participant {

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

    private int checkAceScore(int totalSum) {
        if (totalSum + ALTERNATE_ACE_VALUE > BLACKJACK_NUMBER) {
            return totalSum;
        }
        return totalSum + ALTERNATE_ACE_VALUE;
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(card -> card.getNumber() == ACE_NUMBER);
    }

    protected int calculateWithoutAce() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    public boolean isBurst() {
        return calculateScore() > BLACKJACK_NUMBER;
    }

    public boolean isHigherThan(final Participant other) {
        final int thisScore = this.calculateScore();
        final int otherScore = other.calculateScore();
        return thisScore > otherScore;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getScore() {
        return calculateScore();
    }
}
