package domain;

import java.util.ArrayList;
import java.util.List;

// TODO: 2023/03/02 추상클래스로 변경
public class Participant {
    private final List<Card> cards = new ArrayList<>();

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int countOfAce = countAce();
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        return applyAce(score, countOfAce);
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int applyAce(int score, int aceCount) {
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount -= 1;
        }

        return score;
    }

    public boolean isBusted() {
        return calculateScore() > 21;
    }

    public int cardSize() {
        return cards.size();
    }
}
