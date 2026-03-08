package controller;

import dto.GameStatus;
import dto.NameResponse;
import dto.PlayerNamesRequest;
import dto.SelectRequest;
import service.BlackJackCommandService;
import service.BlackJackQueryService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final BlackJackCommandService commandService;
    private final BlackJackQueryService queryService;

    public BlackJackController(BlackJackCommandService commandService, BlackJackQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    public void run() {
        setupPhase();
        playerGamePhase();
        dealerGamePhase();
        resultPhase();
    }

    private void setupPhase() {
        setupGameTable();
        setupPlayers();

        OutputView.printTaskDivider();

        distributeInitialCards();
        displayInitialCards();

        OutputView.printTaskDivider();
    }

    private void setupGameTable() {
        commandService.setupGameTable();
    }

    private void setupPlayers() {
        PlayerNamesRequest request = InputView.readPlayers();
        commandService.setupPlayers(request.names());
    }

    private void distributeInitialCards() {
        OutputView.distributeCards(queryService.allPlayerNames());
        commandService.distributeInitialCards();
    }

    private void displayInitialCards() {
        OutputView.initDealerCardInfos(queryService.dealerCards());
        OutputView.initAllPlayerCardInfos(queryService.AllPlayersCards());
    }

    private void playerGamePhase() {
        while (queryService.hasWaitingPlayers()) {
            playerGameProcess();
        }
    }

    private void playerGameProcess() {
        NameResponse currentPlayer = queryService.currentPlayerName();
        SelectRequest select = InputView.readSelect(currentPlayer);

        firstPlayerGameProcess(select.isPositive());

        if(select.isPositive()) {
            playerGameLoop();
        }
        commandService.recordCurrentGameResult();
    }

    private void firstPlayerGameProcess(boolean isPositive) {
        if (isPositive) {
            commandService.currentPlayerDrawCard();
        }
        OutputView.playerCardInfos(queryService.currentPlayerCards());
    }

    private void playerGameLoop() {
        boolean wantMoreCard = true;
        while (wantMoreCard && queryService.isCurrentPlayerPlayable()) {
            SelectRequest selected = InputView.readSelect(queryService.currentPlayerName());
            wantMoreCard = selected.isPositive();

            playerGameLoopProcess(wantMoreCard);
        }
    }

    private void playerGameLoopProcess(boolean isRequestedMoreCard) {
        if (isRequestedMoreCard) {
            commandService.currentPlayerDrawCard();
            OutputView.playerCardInfos(queryService.currentPlayerCards());
        }
    }

    private void dealerGamePhase() {

    }

    private void resultPhase() {

    }
}
