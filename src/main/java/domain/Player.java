package domain;

import java.util.List;

public class Player {

    private final List<Card> cards;

    public Player(List<Card> cards) {
        this.cards = cards;
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            var letter = card.getLetter();
            score += getScoreFrom(letter);
        }

        return score;
    }

    private int getScoreFrom(String letter) {
        if (letter.equals("K") || letter.equals("Q") || letter.equals("J")) {
            return 10;
        }
        if (letter.equals("A")) {
            return 1;
        }

        return Integer.parseInt(letter);
    }

}


