package controller;

import static view.OutputView.initDealerCardInfos;

import common.Constants;
import dto.GameStatus;
import dto.PlayerNamesRequest;
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
        OutputView.initAllPlayerCardInfos(queryService.playerCards());
    }

    private void playerGamePhase() {
        InputView.readSelect(queryService.currentPlayerName());

    }



    private void dealerGamePhase() {

    }

    private void resultPhase() {

    }
}
