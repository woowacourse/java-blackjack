package blackjack.model.participant;

import blackjack.model.deck.Card;
import blackjack.model.deck.Score;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards = new ArrayList<>();

    public Hand() {
    }

    public void add(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int aceCount = countAce();
        int totalScoreWithoutAce = calculateBaseScore();

        return Score.calculateAceScore(aceCount, totalScoreWithoutAce) + totalScoreWithoutAce;
    }

    private int calculateBaseScore() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScoreValue)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_CARD_SIZE && calculateScore() == BLACKJACK_SCORE;
    }

    public boolean hasManyThan(Hand other) {
        return this.cards.size() > other.cards.size();
    }

    public boolean hasSameSizeWith(Hand other) {
        return this.cards.size() == other.cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }
}
