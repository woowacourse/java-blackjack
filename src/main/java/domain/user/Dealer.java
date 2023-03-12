package domain.user;

import domain.card.Card;
import domain.card.Score;
import domain.state.DealerStatus;
import domain.state.UserStatus;

import java.util.List;

public class Dealer extends User {
    private DealerStatus status;

    public Dealer(List<Card> firstTurnCards) {
        super(firstTurnCards);
        checkBustByScore();
    }

    @Override
    protected void checkBustByScore() {
        // TODO : hand 메서드 기능 구현 후 수정하기
        if (hand.isBust()) {
            status = DealerStatus.BUST;
            return;
        }
        // TODO : 16 스코어 값 분리하기
        if (hand.calculateScore().isLessThanOrEqual(new Score(16))) {
            status = DealerStatus.UNDER_SEVENTEEN;
            return;
        }
        status = DealerStatus.NORMAL;
    }

    @Override
    public boolean isUserStatus(UserStatus status) {
        return this.status.equals(status);
    }

    @Override
    public String getName() {
        return "딜러";
    }
}
