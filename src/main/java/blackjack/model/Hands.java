package blackjack.model;

import blackjack.model.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hands {

    private final List<Card> cards;

    private Hands(List<Card> cards) {
        this.cards = cards;
    }

    public static Hands empty() {
        return new Hands(new ArrayList<>());
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateTotalScore() {
        int baseScore = this.cards.stream()
                .mapToInt(Card::getDefaultScore)
                .sum();

        boolean hasAce = cards.stream()
                .anyMatch(Card::isAce);

        int adjustmentThreshold = 11;
        int aceAdjustment = 10;

        if (hasAce && baseScore <= adjustmentThreshold) {
            baseScore += aceAdjustment;
        }

        return baseScore;
    }

    public boolean isTotalScoreOver(final int score) {
        return calculateTotalScore() > score;
    }

    public List<Card> getOpenedCards() {
        return List.copyOf(this.cards.stream()
                .filter(Card::isOpened)
                .toList());
    }

    public List<Card> getAllCard() {
        return List.copyOf(cards);
    }

}
