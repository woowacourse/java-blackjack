package domain.state.finished;

import domain.card.vo.Card;
import domain.participants.Hand;
import domain.state.Result;
import domain.state.State;
import java.util.List;
import java.util.function.Function;

public abstract class Finished implements State {
    private final Hand hand;

    public Finished(Hand hand) {
        this.hand = hand;
    }

    abstract public Function<Integer, Integer> earningRate(Result result);

    abstract public Result getResult(State dealerState);

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State drawCard(Card card) {
        throw new IllegalStateException(
                "끝난 State 입니다. 카드를 뽑을 수 없습니다. hand: %s, State: %s"
                        .formatted(hand.getCards().toString(), toString()));
    }

    @Override
    public Integer getScore() {
        return hand.getScore();
    }

    @Override
    public List<Card> getCards() {
        return hand.getCards();
    }

    @Override
    public State stay() {
        throw new IllegalStateException(
                "끝난 State 입니다. stay 할 수 없습니다. hand: %s, State: %s".formatted(
                        hand.toString(), toString()));
    }

    @Override
    public Integer getProfit(State dealerState, Integer betCost) {
        return earningRate(getResult(dealerState)).apply(betCost);
    }
}
