package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.cards.Cards;
import blackjack.model.cards.MaxScoreCalculator;
import blackjack.model.cards.Score;

public final class Dealer extends Player {

    private static final MaxScoreCalculator MAX_SCORE_CALCULATOR = new MaxScoreCalculator();
    private static final String NAME = "딜러";
    private static final Score HIT_BOUNDARY = new Score(17);
    private static final int OPEN_CARD_COUNT = 1;

    public Dealer(Card card1, Card card2, Card... cards) {
        super(NAME, new Cards(card1, card2, cards));
    }

    @Override
    public Cards openCards() {
        return cards().openedCards(OPEN_CARD_COUNT);
    }

    @Override
    public boolean isHittable() {
        return maxScore().lessThan(HIT_BOUNDARY);
    }

    private Score maxScore() {
        return MAX_SCORE_CALCULATOR.calculate(cards());
    }
}
