package bank;

import ScoreResult.BattleResult;
import ScoreResult.ScoreBoard;
import java.util.LinkedHashMap;
import participant.Participant;
import java.util.Collections;
import java.util.Map;

public class PlayerBettingRecord {
    private final Map<Participant, Money> gamblingStatement;

    public PlayerBettingRecord(final Map<Participant, Money> gamblingStatement) {
        this.gamblingStatement = new LinkedHashMap<>(gamblingStatement);
    }

    public PlayerBettingRecord calculateProfit(final ScoreBoard scoreBoard) {
        Map<Participant, Money> profitStatement = new LinkedHashMap<>();
        for (Map.Entry<Participant, Money> entry : gamblingStatement.entrySet()) {
            Participant participant = entry.getKey();
            BattleResult battleResult = scoreBoard.requestBattleResult(participant);
            Money money = battleResult.calculateProfit(entry.getValue());
            profitStatement.put(participant, money);
        }
        return new PlayerBettingRecord(profitStatement);
    }

    public Map<Participant, Money> getGamblingStatement() {
        return Collections.unmodifiableMap(gamblingStatement);
    }
}
