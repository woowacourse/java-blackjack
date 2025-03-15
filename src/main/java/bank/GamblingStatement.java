package bank;

import ScoreResult.ScoreBoard;
import participant.Participant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GamblingStatement {
    private final Map<Participant, Money> gamblingStatement;

    public GamblingStatement(final Map<Participant, Money> gamblingStatement) {
        this.gamblingStatement = new HashMap<>(gamblingStatement);
    }

    public GamblingStatement calculateProfit(final ScoreBoard scoreBoard) {
        Map<Participant, Money> profitStatement = new HashMap<>();
        for (Map.Entry<Participant, Money> entry : gamblingStatement.entrySet()) {
            Participant participant = entry.getKey();
            int winCounts = scoreBoard.requestWinCounts(participant);
            int drawCounts = scoreBoard.requestDrawCounts(participant);
            int loseCounts = scoreBoard.requestLoseCounts(participant);
            Money money = entry.getValue();
            if (participant.isBlackJack() && winCounts != 0) {
                profitStatement.put(participant, Money.multiply(money, 1.5));
                continue;
            }
            if (winCounts != 0) {
                profitStatement.put(participant, Money.multiply(money, 1));
                continue;
            }
            if (drawCounts != 0) {
                profitStatement.put(participant, Money.multiply(money, 0));
                continue;
            }
            if (loseCounts != 0) {
                profitStatement.put(participant, Money.multiply(money, -1));
            }
        }
        return new GamblingStatement(profitStatement);
    }

    public Map<Participant, Money> getGamblingStatement() {
        return Collections.unmodifiableMap(gamblingStatement);
    }
}
