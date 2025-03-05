package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        return cards.stream()
                .map(card -> card.rank().getValue())
                .reduce(0, Integer::sum);
    }

    public GameStatus determineGameStatus(Cards otherCards) {
        int cardsScore = calculateScore();
        int otherCardsScore = otherCards.calculateScore();
        if (!isGameOver(cardsScore) && !isGameOver(otherCardsScore)) {
            return evaluateStatusByScore(cardsScore, otherCardsScore);
        }
        return evaluateStatusByGameOver(cardsScore, otherCardsScore);
    }

    public Card pickCard() {
        return cards.removeFirst();
    }

    public void shuffle() {
        Collections.shuffle(cards);
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
        return score > 21;
    }
}
