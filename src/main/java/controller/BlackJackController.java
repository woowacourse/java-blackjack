package controller;

import dto.PlayerNamesRequest;
import service.BlackJackCommandService;
import service.BlackJackQueryService;
import view.InputView;

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
        getSetupPlayers();
    }

    private void setupGameTable() {
        commandService.setupGameTable();
    }

    private void getSetupPlayers() {
        PlayerNamesRequest request = InputView.readPlayers();
        commandService.setupPlayers(request.names());
    }

    private void dealerGamePhase() {

    }

    private void resultPhase() {

    }

    private void playerGamePhase() {

    }
}
