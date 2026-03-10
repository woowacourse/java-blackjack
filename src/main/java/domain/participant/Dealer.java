package domain.participant;

import java.util.HashMap;
import java.util.Map;

public class Dealer extends Participant {
    private Map<WinStatus, Integer> record = new HashMap() {{
        put(WinStatus.WIN, 0);
        put(WinStatus.DRAW, 0);
        put(WinStatus.LOSS, 0);
    }};

    private static final int DEALER_HIT_CONDITION = 17;

    public Dealer(HandCards handCards) {
        super(handCards);
    }

    public boolean isDealerHit(){
        if (getScore() < DEALER_HIT_CONDITION) {
            return true;
        }

        return false;
    }

    public Map<WinStatus, Integer> getRecord() {
        return record;
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public void finalizeResult(int playerScore) {
        if (this.isBust() || this.getScore() < playerScore) {
            record.put(WinStatus.LOSS, record.get(WinStatus.LOSS) + 1);
        }

        if (this.getScore() > playerScore) {
            record.put(WinStatus.WIN, record.get(WinStatus.WIN) + 1);
        }

        if (this.getScore() == playerScore) {
            record.put(WinStatus.DRAW, record.get(WinStatus.DRAW) + 1);
        }
    }
}
