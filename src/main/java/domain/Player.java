package domain;

import java.util.List;

public class Player {

    private final List<Card> cards;
    private int score;

    public Player(List<Card> cards) {
        this.cards = cards;
        this.score = calculateScore();
    }

    private int calculateScore() {
        int countA = 0;

        int score = 0;
        for (Card card : cards) {
            var letter = card.getLetter();

            if (letter.equals("A")) {
                ++countA;
                continue;
            }

            score += getScoreFrom(letter);
        }

        for (int i = 0; i < countA; i++) {
            if (score + 11 > 21) {
                score += 1;
                continue;
            }

            score += 11;
        }

        return score;
    }

    private int getScoreFrom(String letter) {
        if (letter.equals("K") || letter.equals("Q") || letter.equals("J")) {
            return 10;
        }

        return Integer.parseInt(letter);
    }

    public int getScore() {
        return score;
    }

    public boolean isBusted() {
        return score > 21;
    }

    public void addCard(Card card) {
        cards.add(card);
        this.score = calculateScore();
    }
}


