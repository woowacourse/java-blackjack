package blackjack.card;

import java.util.List;

public class Hand {

    private static final int ACE_DIFF = 10;
    private static final int THRESHOLD_WITH_SMALL_ACE = 11;
    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> openAllCards() {
        return cards;
    }

    public int sumCards() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
        while (aceCount > 0) {
            sum = addAceDiff(sum);
            aceCount--;
        }
        return sum;
    }

    private static int addAceDiff(int sum) {
        if (sum <= THRESHOLD_WITH_SMALL_ACE) {
            sum += ACE_DIFF;
        }
        return sum;
    }
}
