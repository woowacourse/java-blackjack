package blackjack.domain.deck;

import java.util.ArrayList;
import java.util.List;

public class Hands {

    private final List<Card> cards;

    public Hands(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int calculateScore() {
        int totalScore = 0;
        for (Card card : cards) {
            Rank rank = card.getRank();
            totalScore += rank.getScore(totalScore);
        }
        return totalScore;
    }
}
