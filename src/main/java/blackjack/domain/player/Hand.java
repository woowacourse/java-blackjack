package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.rule.Score;
import blackjack.domain.rule.ScoreCalculateStrategy;
import java.util.List;

public class Hand {

    private static final int BLACK_JACK = 21;
    private static final int ACE_WEIGHT = 10;

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
        int aceCount = countAce();
        int sum = calculateCardSummation();
        while (aceCount > 0 && (sum + ACE_WEIGHT) <= BLACK_JACK) {
            sum += ACE_WEIGHT;
            aceCount--;
        }
        return new Score(sum);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
