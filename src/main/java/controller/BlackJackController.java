package controller;

import dto.response.AllPlayerWinningInfoResponse;
import dto.response.DealerWinningStatisticsResponse;
import dto.response.PlayedGameResultResponse;
import dto.response.PlayerGameResultsResponse;
import dto.request.PlayerNamesRequest;
import dto.request.SelectRequest;
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
        OutputView.printTaskDivider();

        distributeInitialCards();
        displayInitialCards();
        OutputView.printTaskDivider();
    }


    private void setupGameTable() {
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
        OutputView.printTaskDivider();
    }

    private void playerGameProcess() {
        SelectRequest select = playerSelect();
        firstPlayerGameProcess(select);

        if (select.isPositive()) {
            playerGameLoop();
        }
        commandService.recordCurrentGameResult();
    }

    private SelectRequest playerSelect() {
        return InputView.readSelect(queryService.currentPlayerName());
    }

    private void firstPlayerGameProcess(SelectRequest select) {
        if (select.isPositive()) {
            commandService.currentPlayerDrawCard();
        }
        OutputView.playerCardInfos(queryService.currentPlayerCards());
    }

    private void playerGameLoop() {
        boolean wantMoreCard = true;
        while (wantMoreCard && queryService.isCurrentPlayerPlayable()) {
            wantMoreCard = playerSelect().isPositive();
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
        if(queryService.isDealerPlayable()) {
            dealerGameLoop();
            commandService.recordDealerGameResult();
            OutputView.printTaskDivider();
        }
        commandService.recordDealerGameResult();
    }

    private void dealerGameLoop() {
        while (queryService.isDealerPlayable()) {
            dealerGameLoopProcess();
        }
    }

    private void dealerGameLoopProcess() {
        if (queryService.isDealerPlayable()) {
            commandService.dealerDrawCard();
            OutputView.dealerDrawCard();
        }
    }

    private void resultPhase() {
        displayDealersResult();
        displayPlayerResults();

        OutputView.printTaskDivider();

        displayDealerWinningStatistics();
        displayPlayerWinningConditions();
    }

    private void displayDealersResult() {
        PlayedGameResultResponse dealerResultResponse = queryService.dealerResult();
        OutputView.participantResult(dealerResultResponse);
    }

    private void displayPlayerResults() {
        PlayerGameResultsResponse playerGameResultsResponse = queryService.playerResults();
        OutputView.playerResults(playerGameResultsResponse);
    }

    private void displayDealerWinningStatistics() {
        OutputView.winningConditionsHeader();
        DealerWinningStatisticsResponse response = queryService.dealerWinningStatistics();
        OutputView.dealerWinningStatistics(response);
    }

    private void displayPlayerWinningConditions() {
        AllPlayerWinningInfoResponse response = queryService.playerWinningInfos();
        OutputView.playerWinningConditions(response);
    }
}
