package blackjack.domain.card;

import blackjack.domain.rule.Score;
import java.util.List;

public class Hand {

    public static final int INITIAL_HAND_SIZE = 2;
    private static final int BUST_THRESHOLD = 21;
    private static final int ACE_WEIGHT = 10;

    private final List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand createHandFrom(CardDeck cardDeck) {
        return new Hand(cardDeck.popCards(INITIAL_HAND_SIZE));
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

    public boolean isBlackJack() {
        return calculateScore().isMaxScore() && cards.size() == INITIAL_HAND_SIZE;
    }

    public Score calculateScore() {
        int aceCount = countAce();
        int sum = calculateCardSummation();
        while (aceCount > 0 && (sum + ACE_WEIGHT) <= BUST_THRESHOLD) {
            sum += ACE_WEIGHT;
            aceCount--;
        }
        return new Score(sum);
    }

    public int countDraw() {
        return cards.size() - INITIAL_HAND_SIZE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
