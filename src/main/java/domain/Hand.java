package domain;

import domain.constant.CardNumber;
import domain.constant.GamerResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int ACE_VALUE_GAP = 10;
    private static final int BUST_THRESHOLD = 21;
    private static final int INITIAL_CARD_COUNT = 2;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getResultScore() {
        int total = getTotalScore();

        if (total <= BUST_THRESHOLD - ACE_VALUE_GAP && containsAce()) {
            return total + ACE_VALUE_GAP;
        }

        return total;
    }

    private int getTotalScore() {
        return cards.stream()
                .map(Card::getCardNumber)
                .map(CardNumber::getScore)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return getResultScore() > BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public GamerResult judge(Hand opponent) {
        return judgeBust(opponent);
    }

    private GamerResult judgeBust(Hand opponent) {
        if (this.isBust()) {
            return GamerResult.LOSE;
        }
        if (opponent.isBust()) {
            return GamerResult.WIN;
        }
        return judgeBlackJack(opponent);
    }

    private GamerResult judgeBlackJack(Hand opponent) {
        if (this.isBlackJack()) {
            return GamerResult.WIN;
        }
        if (opponent.isBlackJack()) {
            return GamerResult.LOSE;
        }
        return judgeScore(opponent);
    }

    private boolean isBlackJack() {
        return this.getResultScore() == BUST_THRESHOLD && cards.size() == INITIAL_CARD_COUNT;
    }

    private GamerResult judgeScore(Hand opponent) {
        if (this.getResultScore() > opponent.getResultScore()) {
            return GamerResult.WIN;
        }
        if (this.getResultScore() < opponent.getResultScore()) {
            return GamerResult.LOSE;
        }
        return GamerResult.DRAW;
    }
}
