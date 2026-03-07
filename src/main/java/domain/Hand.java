package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
            .map(Card::calculateScore)
            .reduce(0, Integer::sum);
        int aceCount = cards.stream()
            .filter(Card::isAce)
            .toList()
            .size();

        return calculateScoreWithBestAce(score, aceCount);
    }

    private int calculateScoreWithBestAce(int score, int aceCount) {
        while (score > 21 && aceCount > 0) { // TODO: 상수?
            score -= 10;
            aceCount--;
        }

        return score;
    }

    public List<String> getCardNames() {
        return cards.stream()
            .map(Card::getName)
            .toList();
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }
}
