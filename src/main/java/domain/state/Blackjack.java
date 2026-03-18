package domain.state;

import domain.Result;
import domain.card.Card;
import domain.card.Hand;

public class Blackjack extends State {
    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalArgumentException("[ERROR] Blackjack 상태에서는 카드를 더 이상 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("[ERROR] Blackjack 상태에서는 stay 할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Result judge(State state) {
        if (state instanceof Blackjack) {
            return Result.DRAW;
        }
        return Result.BLACKJACK;
    }
}
