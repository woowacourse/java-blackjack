package blackjack.domain.user;

import blackjack.domain.Result;
import blackjack.domain.card.Card;

import java.util.List;

public class Player extends User {

    public static final String CANNOT_DETERMINE_RESULT = "승패를 계산할 수 없습니다";
    public static final int DRAW_VAL = 0;

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        return getCards();
    }

    public Result compareScore(Dealer dealer) {
        if (isDraw(dealer)) {
            return Result.DRAW;
        }
        if (isLose(dealer)) {
            return Result.LOSE;
        }
        if (isWin(dealer)) {
            return Result.WIN;
        }
        throw new RuntimeException(CANNOT_DETERMINE_RESULT);
    }

    private boolean isDraw(Dealer dealer) {
        if (dealer.isBusted() && this.isBusted()) {
            return true;
        }
        return dealer.compareScoreWith(this.getTotalScore()) == DRAW_VAL;
    }

    private boolean isLose(Dealer dealer) {
        if (!dealer.isBusted() && this.isBusted()) {
            return true;
        }
        return dealer.compareScoreWith(this.getTotalScore()) < DRAW_VAL;
    }

    private boolean isWin(Dealer dealer) {
        if (dealer.isBusted() && !this.isBusted()) {
            return true;
        }
        return dealer.compareScoreWith(this.getTotalScore()) > DRAW_VAL;
    }

}
