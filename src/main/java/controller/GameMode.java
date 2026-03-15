package controller;

import controller.input.BettingPlayerReader;
import controller.input.NonBettingPlayerReader;
import controller.input.PlayerReader;
import controller.result.BettingResultReporter;
import controller.result.NonBettingResultReporter;
import controller.result.ResultReporter;
import model.judgement.PlayerResult;
import model.paticipant.BettingPlayer;
import model.paticipant.Player;
import model.paticipant.Players;

public class GameMode<T extends Player> {

    private final PlayerReader<T> playerReader;
    private final ResultReporter<T> resultReporter;

    private GameMode(PlayerReader<T> playerReader, ResultReporter<T> resultReporter) {
        this.playerReader = playerReader;
        this.resultReporter = resultReporter;
    }

    public static GameMode<BettingPlayer> toBettingMode() {
        return new GameMode<>(new BettingPlayerReader(), new BettingResultReporter());
    }

    public static GameMode<Player> toNonBettingMode() {
        return new GameMode<>(new NonBettingPlayerReader(), new NonBettingResultReporter());
    }

    public Players<T> createPlayers() {
        return playerReader.readPlayers();
    }

    public void reportResult(PlayerResult<T> playerResult) {
        resultReporter.report(playerResult);
    }
}
