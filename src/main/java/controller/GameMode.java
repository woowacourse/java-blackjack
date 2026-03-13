package controller;

import controller.input.BettingPlayerReader;
import controller.input.NonBettingPlayerReader;
import controller.input.PlayerReader;
import controller.result.BettingResultReporter;
import controller.result.NonBettingResultReporter;
import controller.result.ResultReporter;
import model.judgement.Judgement;
import model.judgement.PlayerResult;
import model.paticipant.Dealer;
import model.paticipant.Players;
import view.OutputView;

public class GameMode {

    private final PlayerReader playerReader;
    private final ResultReporter resultReporter;

    private GameMode(PlayerReader playerReader, ResultReporter resultReporter) {
        this.playerReader = playerReader;
        this.resultReporter = resultReporter;
    }

    public static GameMode toBettingMode() {
        return new GameMode(new BettingPlayerReader(), new BettingResultReporter());
    }

    public static GameMode toNonBettingMode() {
        return new GameMode(new NonBettingPlayerReader(), new NonBettingResultReporter());
    }

    public Players createPlayers() {
        return playerReader.readPlayers();
    }

    public void reportResult(Dealer dealer, Players players) {
        printFinalCards(dealer, players);

        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, players);
        resultReporter.report(playerResult);
    }

    private void printFinalCards(Dealer dealer, Players players) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(dealer);
        players.forEach(OutputView::printCardByPlayerWithScore);
    }
}
