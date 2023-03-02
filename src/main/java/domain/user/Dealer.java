package domain.user;

import domain.card.Card;
import java.util.List;

public class Dealer extends User{
    private DealerStatus status;

    public Dealer(List<Card> firstTurnCards) {
        super(firstTurnCards);
        checkBustByScore();
    }

    @Override
    protected void checkBustByScore() {
        if(score.getScore() > BLACKJACK) {
            status = DealerStatus.BUST;
            return;
        }
        if (score.getScore() <= 16) {
            status = DealerStatus.UNDER_SEVENTEEN;
            return;
        }
        status = DealerStatus.NORMAL;
    }

    @Override
    protected UserStatus getStatus() {
        return status;
    }
}
