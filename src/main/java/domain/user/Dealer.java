package domain.user;

import domain.card.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends User {
    private DealerStatus status;
    private Map<Boolean, Integer> winningRecord;

    public Dealer(List<Card> firstTurnCards) {
        super(firstTurnCards);
        initWinningRecord();
        checkBustByScore();
    }

    private void initWinningRecord() {
        winningRecord = new HashMap<>();
        winningRecord.put(true, 0);
        winningRecord.put(false, 0);
    }

    @Override
    public void win() {
        winningRecord.put(true, winningRecord.getOrDefault(true, 0) + 1);
    }

    @Override
    public void lose() {
        winningRecord.put(false, winningRecord.getOrDefault(false, 0) + 1);
    }

    @Override
    protected void checkBustByScore() {
        if (score.getScore() > BLACKJACK) {
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
    public boolean isUserStatus(UserStatus status) {
        return this.status.equals(status);
    }

    @Override
    public String getName() {
        return "딜러";
    }

    public Map<Boolean, Integer> getWinningRecord() {
        return winningRecord;
    }
}
