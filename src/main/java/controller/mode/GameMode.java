package controller.mode;

import controller.input.PlayerReader;
import controller.result.ResultReporter;
import model.judgement.PlayerResult;
import model.paticipant.Players;

public abstract class GameMode {

    private final PlayerReader playerReader;
    private final ResultReporter resultReporter;

    protected GameMode(PlayerReader playerReader, ResultReporter resultReporter) {
        this.playerReader = playerReader;
        this.resultReporter = resultReporter;
    }

    public Players createPlayers() {
        return playerReader.readPlayers();
    }

    public void reportResult(PlayerResult playerResult) {
        resultReporter.report(playerResult);
    }
}
