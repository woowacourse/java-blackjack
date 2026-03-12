package domain.participant;

import java.util.HashMap;
import java.util.Map;

public class Dealer extends Participant {
    private static final int DEALER_HIT_CONDITION = 17;
    private final Map<WinStatus, Integer> record = new HashMap() {{
        put(WinStatus.WIN, 0);
        put(WinStatus.DRAW, 0);
        put(WinStatus.LOSS, 0);
    }};

    public Dealer(HandCards handCards) {
        super(handCards);
    }

    public boolean isDealerHit(){
        return getScore() < DEALER_HIT_CONDITION;
    }

    public Map<WinStatus, Integer> getRecord() {
        return record;
    }

    public void saveResult(WinStatus playerWinstatus) {
        if (playerWinstatus == WinStatus.LOSS) {
            record.put(WinStatus.WIN, record.get(WinStatus.WIN) + 1);
        }

        if (playerWinstatus == WinStatus.WIN) {
            record.put(WinStatus.LOSS, record.get(WinStatus.LOSS) + 1);
        }

        if (playerWinstatus == WinStatus.DRAW) {
            record.put(WinStatus.DRAW, record.get(WinStatus.DRAW) + 1);
        }
    }
}
