package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BURST_THRESHOLD = 21;
    private static final int ACE_VALUE_GAP = 10;
    List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }


    public int getResultScore() {
        int total = getTotalScore();

        if (total <= BURST_THRESHOLD - ACE_VALUE_GAP && containsAce()) {
            return total + ACE_VALUE_GAP;
        }

        return total;
    }

    private int getTotalScore() {
        return cards.stream()
                .map(Card::getCardNumber)
                .map(CardNumber::getScore)
                .reduce(Integer::sum)
                .orElse(0);
    }


    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }
}
