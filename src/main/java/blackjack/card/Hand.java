package blackjack.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int ACE_DIFF = 10;
    private static final int THRESHOLD_WITH_SMALL_ACE = 11;
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> openAllCards() {
        return cards;
    }

    public Card openOneCard() {
        return cards.getFirst();
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

    public int countCards() {
        return cards.size();
    }

    private static int addAceDiff(int sum) {
        if (sum <= THRESHOLD_WITH_SMALL_ACE) {
            sum += ACE_DIFF;
        }
        return sum;
    }
}
