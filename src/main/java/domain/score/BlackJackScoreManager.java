package domain.score;

import domain.card.possessable.CardPossessable;
import domain.card.possessable.HandCards;

public class BlackJackScoreManager implements ScoreManagable {


    private BlackJackScoreManager() {
    }

    public static Calculatable calculate(HandCards handCards) {
        Calculatable defaultSum = handCards.calculateDefaultSum();

        if (handCards.hasAce()) {
            return updateAceScore(defaultSum);
        }

        return defaultSum;
    }

    private static Calculatable updateAceScore(Calculatable score) {
        Calculatable bigAceScore = score.plus(ACE_ADDITIONAL_SCORE);
        if (bigAceScore.isLargerThan(BLACK_JACK_SCORE)) {
            return score;
        }

        return score.plus(ACE_ADDITIONAL_SCORE);
    }

    @Override
    public Calculatable calculate(CardPossessable cardPossessable) {
        Calculatable defaultSum = cardPossessable.calculateDefaultSum();

        if (cardPossessable.hasAce()) {
            return updateAceScore(defaultSum);
        }

        return defaultSum;
    }
}
