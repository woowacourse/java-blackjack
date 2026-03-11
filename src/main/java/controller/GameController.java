package controller;

import domain.Dealer;
import domain.GameTable;
import domain.Hand;
import domain.Participant;
import domain.Player;
import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import strategy.RandomStrategy;
import view.InputView;
import view.OutputView;

public class GameController {
    private static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ",";
    private static final String POSITIVE = "y";
    private static final int DRAW_COUNT = 2;

    private GameTable gameTable;

    public void run() {
        setupPhase();
        playerGamePhase();
        dealerGamePhase();
        resultPhase();
    }

    private void setupPhase() {
        gameTable = new GameTable();
        dealerSetup();
        playerSetup();
        displayInitCard();
    }

    private void playerGamePhase() {
        gameTable.rotateParticipant();
        while (gameTable.isPlayer()) {
            playerExecute(gameTable.currentPlayerName());
        }
        OutputView.printTaskDivider();
    }

    private void dealerGamePhase() {
        while (gameTable.isDealerPlayable()) {
            gameTable.playDealer();
            OutputView.dealerStay();
        }
        OutputView.printTaskDivider();
    }

    private void resultPhase() {
        List<GameStatus> gameStatuses = gameTable.endedGameStatus();
        OutputView.participantsResults(gameStatuses);

        List<GameResult> result = gameTable.result();
        OutputView.gameResult(result);
    }

    private void displayInitCard() {
        List<GameStatus> gameStatuses = gameTable.initGameStatus();
        OutputView.initCardStatus(gameStatuses);
    }

    private void dealerSetup() {
        Hand hand = new Hand(new RandomStrategy(), new ArrayList<>());
        Participant dealer = new Dealer(DEALER_NAME, hand);
        drawSetup(dealer);
        gameTable.addParticipant(dealer);
    }

    private void playerSetup() {
        String playerNames = InputView.readPlayers();
        List<String> names = Arrays.stream(playerNames.split(DELIMITER)).toList();
        OutputView.divideCards(names);

        for (String name : names) {
            Hand hand = new Hand(new RandomStrategy(), new ArrayList<>());
            Participant player = new Player(name, hand);
            drawSetup(player);
            gameTable.addParticipant(new Player(name, hand));
        }
    }

    private void drawSetup(Participant participant) {
        IntStream.range(0, DRAW_COUNT).forEach(i -> participant.draw());
    }

    private void playerExecute(String name) {
        String select = InputView.readSelect(name);
        initProcess(select);

        if (select.equals(POSITIVE)) {
            playLoop(name);
        }

        gameTable.rotateParticipant();
    }

    private void initProcess(String select) {
        if (select.equals(POSITIVE)) {
            gameTable.playCurrentPlayer();
        }
        GameStatus gameStatus = gameTable.currentPlayerStatus();
        OutputView.printGameLog(gameStatus);
    }

    private void playLoop(String name) {
        String select = POSITIVE;
        while (select.equals(POSITIVE) && gameTable.isCurrentPlayerPlayable()) {
            select = InputView.readSelect(name);
            play(select);
        }
    }

    private void play(String select) {
        if (select.equals(POSITIVE)) {
            gameTable.playCurrentPlayer();
            GameStatus gameStatus = gameTable.currentPlayerStatus();
            OutputView.printGameLog(gameStatus);
        }
    }
}
