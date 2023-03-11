package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Participant {
    private static final int MAX_NUMBER = 21;
    private final Name name;
    private final Cards cards;

    public Participant(final Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void drawCard(final Card card) {
        cards.add(card);
    }


    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public int getActualScore() {
        return this.cards.calculateTotalScore();
    }


    public int getTotalScore() {
        if (cards.isBlackjack()) {
            return Integer.MAX_VALUE;
        }
        return limitNumber(this.cards.calculateTotalScore());
    }

    private int limitNumber(int totalScore) {
        if (totalScore > MAX_NUMBER) {
            return Integer.MIN_VALUE;
        }
        return totalScore;
    }

    public boolean isBust() {
        return this.cards.calculateTotalScore() > MAX_NUMBER;
    }

    public boolean isNotBust() {
        return this.cards.calculateTotalScore() <= MAX_NUMBER;
    }
}
