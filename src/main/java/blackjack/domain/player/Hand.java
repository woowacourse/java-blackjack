package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreCalculateStrategy;

import java.util.List;

public class Hand {

    private final List<Card> cards;
    private final ScoreCalculateStrategy scoreCalculateStrategy;

    public Hand(List<Card> cards, ScoreCalculateStrategy scoreCalculateStrategy) {
        this.cards = cards;
        this.scoreCalculateStrategy = scoreCalculateStrategy;
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public void append(Card card) {
        cards.add(card);
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int countCard() {
        return cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
