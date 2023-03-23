package domain.blackjack.gamestate;

import domain.blackjack.BlackjackScore;
import domain.blackjack.Result;
import domain.card.Card;
import domain.card.Cards;

public class Playing extends GameState {
    public Playing(Cards cards, HandState handState) {
        super(cards, handState);
    }

    public static GameState from(Cards cards) {
        validateSize(cards);
        if (BlackjackScore.from(cards).isEqualTo(BlackjackScore.getMaxScore())) {
            return new Blackjack(cards, HandState.BLACKJACK);
        }

        return new Playing(cards, HandState.STAY);
    }

    private static void validateSize(Cards cards) {
        if (cards.getCards().size() != 2) {
            throw new IllegalStateException();
        }
    }

    @Override
    public GameState receive(Card card) {
        cards.add(card);

        BlackjackScore blackjackScore = BlackjackScore.from(cards);
        if (blackjackScore.isGreaterThan(BlackjackScore.getMaxScore())) {
            return new Bust(cards, HandState.BUST);
        }

        return new Playing(cards, HandState.STAY);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        return true;
    }

    @Override
    public Result competeToOtherState(GameState otherState) {
        if (handState.isEqualState(otherState.handState)) {
            return Result.DRAW;
        }

        Result resultOfOtherState = otherState.competeToOtherState(this);
        return resultOfOtherState.convertToOpposite();
    }

    @Override
    public double getEarningRate() {
        return DEFAULT_EARN_RATE;
    }
}
