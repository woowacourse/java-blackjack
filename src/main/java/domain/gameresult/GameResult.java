package domain.gameresult;

import domain.player.Gambler;
import domain.player.Player;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Result, Integer> resultOfDealer = new HashMap<>();
    private final Map<Player, Result> resultOfParticipants = new HashMap<>();

    public GameResult() {
        for (Result result : Result.values()) {
            resultOfDealer.put(result, 0);
        }
    }

    public void addDrawCount(Player participant) {
        addResultCountOfDealer(Result.DRAW);
        resultOfParticipants.put(participant, Result.DRAW);
    }

    public void addParticipantWinningCase(Player participant) {
        addResultCountOfDealer(Result.LOSE);
        resultOfParticipants.put(participant, Result.WIN);
    }

    private void addResultCountOfDealer(Result result) {
        resultOfDealer.put(result, resultOfDealer.get(result) + 1);
    }

    public void addParticipantLosingCase(Player participant) {
        addResultCountOfDealer(Result.WIN);
        resultOfParticipants.put(participant, Result.LOSE);
    }

    public int getWinningCountOfDealer() {
        return this.resultOfDealer.get(Result.WIN);
    }

    public int getLosingCountOfDealer() {
        return this.resultOfDealer.get(Result.LOSE);
    }

    public int getDrawingCountOfDealer() {
        return this.resultOfDealer.get(Result.DRAW);
    }

    public Result getResultByParticipant(Gambler gambler) {
        return this.resultOfParticipants.get(gambler);
    }

    protected Map<Player, Result> getAllParticipantResults() {
        return resultOfParticipants;
    }
}
