package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    public static final int BLACKJACK_SCORE = 21;
    private static final int ACE_PLUS_SCORE = 10;

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
        while (aceCount-- > 0 && !isGameOver(score + ACE_PLUS_SCORE)) {
            score += ACE_PLUS_SCORE;
        }
        return score;
    }

    public GameStatus determineGameStatus(Cards otherCards) {
        int cardsScore = calculateScore();
        int otherCardsScore = otherCards.calculateScore();
        if (!isGameOver(cardsScore) && !isGameOver(otherCardsScore)) {
            return evaluateStatusByScore(cardsScore, otherCardsScore);
        }
        return evaluateStatusByGameOver(cardsScore, otherCardsScore);
    }

    public int calculateAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pickCard() {
        return cards.removeFirst();
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

    private GameStatus evaluateStatusByGameOver(int cardsScore, int otherCardsScore) {
        if (!isGameOver(cardsScore) && isGameOver(otherCardsScore)) {
            return GameStatus.WIN;
        }
        if (isGameOver(cardsScore) && !isGameOver(otherCardsScore)) {
            return GameStatus.LOSE;
        }
        return GameStatus.TIE;
    }

    private boolean isGameOver(int score) {
        return score > BLACKJACK_SCORE;
    }
}
