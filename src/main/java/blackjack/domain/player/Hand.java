package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.rule.HitStrategy;
import blackjack.domain.rule.Score;
import blackjack.domain.rule.ScoreCalculateStrategy;
import java.util.List;

public class Hand {

    private final List<Card> cards;
    private final ScoreCalculateStrategy scoreCalculateStrategy;
    private final HitStrategy hitStrategy;

    public Hand(List<Card> cards, ScoreCalculateStrategy scoreCalculateStrategy, HitStrategy hitStrategy) {
        this.cards = cards;
        this.scoreCalculateStrategy = scoreCalculateStrategy;
        this.hitStrategy = hitStrategy;
    }

    public int sum() {
        return cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getValue)
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

    public Score calculateScore() {
        return scoreCalculateStrategy.calculate(this);
    }

    public boolean canHit() {
        return hitStrategy.canHit(calculateScore());
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
