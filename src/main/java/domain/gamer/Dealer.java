package domain.gamer;

import domain.card.Card;
import domain.result.Result;
import domain.result.ResultDerivable;
import domain.result.score.ScoreCalculable;

import java.util.Arrays;
import java.util.List;

import static domain.result.BlackJackRule.DEALER_MAXIMUM_CARDS_AMOUNT;
import static domain.result.BlackJackRule.DEALER_THRESHOLD_SCORE;

public class Dealer extends Gamer {
    public static final Name DEALER_NAME = new Name("딜러");

    public Dealer() {
        super(DEALER_NAME, new Hand());
    }

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    @Override
    public List<Card> openInitialCards() {
        return Arrays.asList(hand.getFirstCard());
    }

    @Override
    public boolean canDrawMore(ScoreCalculable scoreCalculable) {
        return !calculateScore(scoreCalculable).isBiggerThan(DEALER_THRESHOLD_SCORE)
                && hand.size() < DEALER_MAXIMUM_CARDS_AMOUNT;
    }

    public Result determineResult(List<Result> playerResults, ResultDerivable resultDerivable) {
        return resultDerivable.deriveDealerResult(playerResults);
    }
}
