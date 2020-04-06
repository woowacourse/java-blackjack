package domain.gamer;

import domain.card.Card;
import domain.result.Result;
import domain.result.score.ScoreCalculable;

import java.util.Arrays;
import java.util.List;

import static domain.result.BlackJackRule.DEALER_THRESHOLD_SCORE;

public class Dealer extends Gamer {
    private static final int MAXIMUM_CARDS_AMOUNT = 3;
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
                && hand.size() < MAXIMUM_CARDS_AMOUNT;
    }

    public Result determineResult(List<Result> playerResults) {
        Money totalSum = playerResults.stream()
                .map(Result::getProfit)
                .reduce(new Money(0), (a, b) -> a.plus(b));

        return new Result(DEALER_NAME, totalSum.negate());
    }
}
