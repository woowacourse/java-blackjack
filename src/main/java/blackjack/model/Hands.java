package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.CardDto;
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

        if (hasAce && baseScore <= 11) {
            baseScore += 10;
        }

        return baseScore;
    }

    public boolean isTotalScoreOver(final int score) {
        return calculateTotalScore() > score;
    }

    public List<CardDto> getAllCard() {
        return this.cards.stream()
                .map(Card::toDto)
                .toList();
    }

    public List<CardDto> getOpenedCards() {
        return this.cards.stream()
                .filter(Card::isOpened)
                .map(Card::toDto)
                .toList();
    }
}
