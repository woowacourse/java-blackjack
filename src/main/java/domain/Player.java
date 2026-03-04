package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> holdCards;

    public Player() {
        this.holdCards = new ArrayList<>();
    }

    public void addCard(Card card) {
        holdCards.add(card);
    }

    public int calculateTotalScore() {
        int results = 0;
        for (Card holdCard : holdCards) {
            results += holdCard.getScore();
        }
        return results;
    }

    public boolean isBust(int playerTotalScore) {
        return playerTotalScore > 21;
    }
}
