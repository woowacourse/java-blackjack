package domain.gamer;

import domain.card.Card;
import domain.result.Result;
import domain.result.ResultDerivable;

import java.util.Arrays;
import java.util.List;


public class Dealer extends AbstractGamer {
    public static final Name DEALER_NAME = new Name("딜러");

    public Dealer() {
        super(DEALER_NAME, new Hand());
    }

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    @Override
    public List<Card> openInitialCards() {
        return Arrays.asList(hand.getOneCard());
    }

    public Result determineResult(List<Result> playerResults, ResultDerivable resultDerivable) {
        return resultDerivable.deriveDealerResult(playerResults);
    }
}
