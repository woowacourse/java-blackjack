package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BURST_THRESHOLD = 21;
    List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }


    public int getResultScore() {
        int total = getTotalScore();

        if (total > BURST_THRESHOLD) {
            return changeAces(countAces(), total);
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


    private int countAces() {
        return (int) cards.stream()
                .map(Card::getCardNumber)
                .filter(cardNumber -> cardNumber.equals(CardNumber.DEFAULT_ACE))
                .count();
    }

    private int changeAces(int aceCount, int total) {
        // TODO: 가독성 있게 바꿀 수 있을까?
        int convertedAce = 0;
        while (total > 21 && convertedAce < aceCount) {
            total -= 10;
            convertedAce++;
        }
        return total;
    }
}
