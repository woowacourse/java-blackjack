package domain.user;

import domain.card.Card;
import java.util.HashMap;
import java.util.Map;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int RECORD_INITIAL_VALUE = 0;
    private static final int MIN_SCORE = 17;

    private DealerStatus status;
    private Map<Boolean, Integer> winningRecord;

    public Dealer() {
        super();
        initWinningRecord();
    }

    private void initWinningRecord() {
        winningRecord = new HashMap<>();
        winningRecord.put(true, RECORD_INITIAL_VALUE);
        winningRecord.put(false, RECORD_INITIAL_VALUE);
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }

    @Override
    public void win(User player) {
        winningRecord.put(true, winningRecord.getOrDefault(true, RECORD_INITIAL_VALUE) + 1);
        player.lose();
    }

    @Override
    public void lose() {
        winningRecord.put(false, winningRecord.getOrDefault(false, RECORD_INITIAL_VALUE) + 1);
    }

    @Override
    protected void checkBustByScore() {
        if (score.getScore() > BLACKJACK) {
            status = DealerStatus.BUST;
            return;
        }
        if (score.getScore() < MIN_SCORE) {
            status = DealerStatus.UNDER_MIN_SCORE;
            return;
        }
        status = DealerStatus.NORMAL;
    }

    @Override
    public UserStatus getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    public Map<Boolean, Integer> getWinningRecord() {
        return winningRecord;
    }
}
