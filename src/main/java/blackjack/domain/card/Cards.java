package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int sum() {
        int sumWithoutAce = cards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(card -> card.getCardNumber().getNumber())
            .sum();
        int sumOfAce = getSumOfAce(sumWithoutAce);
        return sumWithoutAce + sumOfAce;
    }

    private int getSumOfAce(int sumWithoutAce) {
        int sumOfAce = 0;
        int aceCardCount = getAceCardCount();
        if (aceCardCount >= 2) {
            sumOfAce += aceCardCount - 1;
        }
        if (aceCardCount >= 1) {
            sumOfAce += aceNumber(sumWithoutAce);
        }
        return sumOfAce;
    }

    private int getAceCardCount() {
        return (int)cards.stream()
            .filter(Card::isAce)
            .count();
    }

    private int aceNumber(int sum) {
        if (sum + 11 > 21) {
            return 1;
        }
        return 11;
    }

    public boolean isBlackjack() {
        if (cards.size() != 2) {
            return false;
        }
        return sum() == 21;
    }
}

