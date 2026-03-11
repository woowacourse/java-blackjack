package domain;

import constant.PolicyConstant;
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
            .map(Card::getScore)
            .reduce(0, Integer::sum);
        int aceCount = cards.stream()
            .filter(Card::isAce)
            .toList()
            .size();

        return calculateScoreWithBestAce(score, aceCount);
    }

    private int calculateScoreWithBestAce(int score, int aceCount) {
        while (score > PolicyConstant.BLACKJACK_SCORE && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCard() {
        return cards.stream()
                .toList();
    }
}
