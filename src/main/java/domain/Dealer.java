package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final String name = "딜러";
    private final List<Card> cards;
    private int score;

    public Dealer(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
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

    public boolean isOverSixteen() {
        return score > 16;
    }

    public void drawCardIfNecessary(Deck deck) {
        // TODO: 메서드 이름 생각해보기
        if (!isOverSixteen()) {
            addCard(deck.drawCard());
        }
    }
}


