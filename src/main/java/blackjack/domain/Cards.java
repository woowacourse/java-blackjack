package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int ACE_BONUS_SCORE = 10;
    private static final int BOUND_OF_CHANGE_ACE_VALUE = 11;
    private static final int NO_BONUS_SCORE = 0;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Cards() {
        this(new ArrayList<>());
    }

    public void addAll(Cards targetCards) {
        cards.addAll(targetCards.cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int aceCounts = getAceCounts();
        int score = calculateScoreWhenAceIsMinimum();
        for (int i = 0; i < aceCounts; i++) {
            score += plusBonusAceScore(score);
        }
        return score;
    }

    private int calculateScoreWhenAceIsMinimum() {
        return cards.stream()
                    .mapToInt(Card::getScore)
                    .sum();
    }

    private int getAceCounts() {
        return (int) cards.stream()
                          .filter(Card::isAce)
                          .count();
    }

    private int plusBonusAceScore(int sum) {
        if (sum <= BOUND_OF_CHANGE_ACE_VALUE) {
            return ACE_BONUS_SCORE;
        }
        return NO_BONUS_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
