package controller;

import dto.request.BettingMoneyRequest;
import dto.response.ProfitResponse;
import dto.response.PlayedGameResultResponse;
import dto.response.PlayerGameResultsResponse;
import dto.request.PlayerNamesRequest;
import dto.request.SelectRequest;
import java.util.List;
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
        PlayerNamesRequest request = InputView.readPlayers();
        setupGameTable(request.names());
        OutputView.printTaskDivider();

        setupPlayerBets(request.names());

        distributeInitialCards();
        displayInitialCards();
        OutputView.printTaskDivider();
    }

    private void setupGameTable(List<String> playerNames) {
        commandService.setupPlayers(playerNames);
    }

    private void setupPlayerBets(List<String> playerNames) {
        playerNames.forEach(this::setupPlayerBet);
    }

    private void setupPlayerBet(String name) {
        BettingMoneyRequest request = InputView.readBettingMoney(name);
        commandService.setupBettingMoney(name, request.money());
        OutputView.printTaskDivider();
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
            runPlayerGameProcesses();
        }
        OutputView.printTaskDivider();
    }

    private void runPlayerGameProcesses() {
        SelectRequest select = playerSelect();
        runPlayerInitGameProcess(select);

        if (select.isPositive()) {
            runPlayerGameLoop();
        }
        commandService.recordCurrentGameResult();
    }

    private SelectRequest playerSelect() {
        return InputView.readSelect(queryService.currentPlayerName());
    }

    private void runPlayerInitGameProcess(SelectRequest select) {
        if (select.isPositive() && queryService.isCurrentPlayerPlayable()) {
            commandService.currentPlayerDrawsCard();
        }
        OutputView.playerCardInfos(queryService.currentPlayerCards());
    }

    private void runPlayerGameLoop() {
        boolean wantMoreCard = true;
        while (wantMoreCard && queryService.isCurrentPlayerPlayable()) {
            wantMoreCard = playerSelect().isPositive();
            runPlayerGameLoopProcess(wantMoreCard);
        }
    }

    private void runPlayerGameLoopProcess(boolean isRequestedMoreCard) {
        if (isRequestedMoreCard) {
            commandService.currentPlayerDrawsCard();
            OutputView.playerCardInfos(queryService.currentPlayerCards());
        }
    }

    private void dealerGamePhase() {
        if (queryService.isDealerPlayable()) {
            runDealerGameLoop();
            OutputView.printTaskDivider();
        }
    }

    private void runDealerGameLoop() {
        while (queryService.isDealerPlayable()) {
            runDealerGameLoopProcess();
        }
    }

    private void runDealerGameLoopProcess() {
        if (queryService.isDealerPlayable()) {
            commandService.dealerDrawCard();
            OutputView.dealerDrawCard();
        }
    }

    private void resultPhase() {
        displayDealersResult();
        displayPlayerResults();

        OutputView.printTaskDivider();

        displayProfitResults();
    }

    private void displayDealersResult() {
        PlayedGameResultResponse dealerResultResponse = queryService.dealerResult();
        OutputView.participantResult(dealerResultResponse);
    }

    private void displayPlayerResults() {
        PlayerGameResultsResponse playerGameResultsResponse = queryService.playerResults();
        OutputView.playerResults(playerGameResultsResponse);
    }

    private void displayProfitResults() {
        OutputView.resultHeader();
        List<ProfitResponse> responses = queryService.allProfits();

        OutputView.allProfits(responses);
    }
}
