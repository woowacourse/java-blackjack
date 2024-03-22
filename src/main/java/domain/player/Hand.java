package domain.player;

import domain.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int A_SCORE_GAP = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARDS_COUNT = 2;

    protected final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int cardsScoreSum = sumAllCards();
        return addAceScore(cardsScoreSum);
    }

    private int sumAllCards() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private int addAceScore(int score) {
        int currentScore = score;
        for (Card card : cards) {
            currentScore = decideAceScore(card, currentScore);
        }
        return currentScore;
    }

    private int decideAceScore(Card card, int currentScore) {
        if (card.isAce() && currentScore + A_SCORE_GAP <= BLACKJACK_SCORE) {
            return currentScore + A_SCORE_GAP;
        }
        return currentScore;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_CARDS_COUNT && calculateScore() == BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
