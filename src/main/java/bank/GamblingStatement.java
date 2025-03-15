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
            Calculator selectedCalculator = selectCalculator(scoreBoard, participant);
            Money money = selectedCalculator.calculate(entry.getValue());
            profitStatement.put(participant, money);
        }
        return new GamblingStatement(profitStatement);
    }

    private Calculator selectCalculator(ScoreBoard scoreBoard, Participant participant) {
        int winCounts = scoreBoard.requestWinCounts(participant);
        int drawCounts = scoreBoard.requestDrawCounts(participant);

        if (participant.isBlackJack() && winCounts != 0) {
            return new BlackjackWinCalculator();
        }

        if (winCounts != 0) {
            return new WinCalculator();
        }

        if (drawCounts != 0) {
            return new DrawCalculator();
        }

        return new LoseCalculator();
    }


    public Map<Participant, Money> getGamblingStatement() {
        return Collections.unmodifiableMap(gamblingStatement);
    }
}
