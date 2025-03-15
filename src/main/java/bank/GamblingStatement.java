package bank;

import ScoreResult.ScoreBoard;
import java.util.LinkedHashMap;
import participant.Participant;
import java.util.Collections;
import java.util.Map;

public class GamblingStatement {
    private final Map<Participant, Money> gamblingStatement;

    public GamblingStatement(final Map<Participant, Money> gamblingStatement) {
        this.gamblingStatement = new LinkedHashMap<>(gamblingStatement);
    }

    public GamblingStatement calculateProfit(final ScoreBoard scoreBoard) {
        Map<Participant, Money> profitStatement = new LinkedHashMap<>();
        for (Map.Entry<Participant, Money> entry : gamblingStatement.entrySet()) {
            Participant participant = entry.getKey();
            Calculator selectedCalculator = scoreBoard.selectCalculator(participant);
            Money money = selectedCalculator.calculate(entry.getValue());
            profitStatement.put(participant, money);
        }
        return new GamblingStatement(profitStatement);
    }

    public Map<Participant, Money> getGamblingStatement() {
        return Collections.unmodifiableMap(gamblingStatement);
    }
}
