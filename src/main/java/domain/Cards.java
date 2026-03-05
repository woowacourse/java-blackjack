package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;
    private int changeAvailableAceCount;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        changeAvailableAceCount = 0;
    }

    public void addCard(Card card) {
        if (card.getScore() == 11) {
            changeAvailableAceCount += 1;
        }
        cards.add(card);
    }

    public boolean canReceiveCard() {
        int sum = calculateScore();

        while (changeAvailableAceCount != 0 && isBurst(sum)) {
            sum -= 10;
            changeAvailableAceCount -= 1;
        }

        if (isBurst(sum) || sum == 21) {
            return false;
        }

        return true;
    }

    public int calculateScore() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getScore();
        }
        return sum;
    }

    private boolean isBurst(int score) {
        if (score > 21) {
            return true;
        }
        return false;
    }
}
