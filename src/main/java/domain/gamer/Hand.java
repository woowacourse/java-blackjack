package domain.gamer;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int CONDITION_DECIDING_A_SCORE = 10;
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
        int scoreExceptAce = sumExceptAceCards();
        return sumAceCards(scoreExceptAce);
    }

    private int sumExceptAceCards() {
        return cards.stream()
                .filter(Card::isNotAce)
                .mapToInt(Card::score)
                .sum();
    }

    private int sumAceCards(int score) {
        int currentScore = score;
        for (Card card : cards) {
            currentScore = addAceScore(card, currentScore);
        }
        return currentScore;
    }

    private int addAceScore(Card card, int currentScore) {
        if (card.isAce()) {
            return currentScore + decideAceScore(currentScore);
        }
        return currentScore;
    }

    private int decideAceScore(int currentScore) {
        if (currentScore <= CONDITION_DECIDING_A_SCORE) {
            return CardNumber.ACE.getScore() + A_SCORE_GAP;
        }
        return CardNumber.ACE.getScore();
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_CARDS_COUNT && calculateScore() == BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
