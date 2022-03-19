package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {

    private static final int MIN_SCORE_STANDARD = 16;
    private static final int WITHOUT_HIDDEN_CARD_INDEX = 1;
    private static final int PROFIT_REVERSE = -1;

    public Dealer(String name) {
        super(name);
    }

    public boolean checkUnderScoreStandard() {
        return state.cardSum() <= MIN_SCORE_STANDARD;
    }

    public List<Card> getHoldingCardsWithoutHidden() {
        return getHoldingCards()
                .subList(WITHOUT_HIDDEN_CARD_INDEX, state.holdingCards().size());
    }

    public double calculateProfit(List<Double> profits) {
        double sum = profits.stream()
                .mapToDouble(profit -> profit)
                .sum();
        return PROFIT_REVERSE * sum;
    }
}
