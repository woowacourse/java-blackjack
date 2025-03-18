package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int ACE_SCORE_DIFFERENCE = 10;
    private static final int CARDS_BUST_THRESHOLD = 21;
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .map(card -> card.rank().getValue())
                .reduce(0, Integer::sum);
        int aceCount = calculateAceCount();
        while (aceCount-- > 0 && !isGameOver(score + ACE_SCORE_DIFFERENCE)) {
            score += ACE_SCORE_DIFFERENCE;
        }
        return score;
    }

    public GameStatus determineGameStatusByScore(Cards otherCards) {
        int cardsScore = calculateScore();
        int otherCardsScore = otherCards.calculateScore();
        return evaluateStatusByScore(cardsScore, otherCardsScore);
    }

    public int calculateAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public Card pickCard() {
        return cards.removeFirst();
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && calculateScore() == CARDS_BUST_THRESHOLD;
    }

    public boolean isBust() {
        return calculateScore() > CARDS_BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private GameStatus evaluateStatusByScore(int cardsScore, int otherCardsScore) {
        if (cardsScore > otherCardsScore) {
            return GameStatus.WIN;
        }
        if (cardsScore < otherCardsScore) {
            return GameStatus.LOSE;
        }
        return GameStatus.TIE;
    }

    private boolean isGameOver(int score) {
        return score > CARDS_BUST_THRESHOLD;
    }
}
