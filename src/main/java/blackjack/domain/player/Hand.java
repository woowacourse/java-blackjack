package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.rule.Score;
import blackjack.domain.rule.ScoreCalculateStrategy;
import java.util.List;

public class Hand {

    private final List<Card> cards;
    private final ScoreCalculateStrategy scoreCalculateStrategy;

    public Hand(List<Card> cards, ScoreCalculateStrategy scoreCalculateStrategy) {
        this.cards = cards;
        this.scoreCalculateStrategy = scoreCalculateStrategy;
    }

    public int calculateCardSummation() {
        return cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getValue)
                .sum();
    }

    public void appendCard(Card card) {
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

    public Score calculateScore() {
        return scoreCalculateStrategy.calculate(this);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
