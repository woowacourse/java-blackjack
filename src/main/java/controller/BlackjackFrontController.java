package controller;

import controller.dto.BettingRequest;
import controller.dto.CardsResultResponse;
import controller.dto.InitialDealResponse;
import controller.dto.PlayerHitResponse;
import controller.dto.ProfitResultResponse;
import java.util.List;
import view.ConsoleView;

public class BlackjackFrontController {

    private final ConsoleView consoleView;
    private final BlackjackController blackjackController;

    public BlackjackFrontController(ConsoleView consoleView, BlackjackController blackjackController) {
        this.consoleView = consoleView;
        this.blackjackController = blackjackController;
    }

    public void startBetting() {
        List<BettingRequest> bettingRequests = consoleView.requestBetting();
        blackjackController.registerBettingPlayers(bettingRequests);
    }

    public void initialDeal() {
        InitialDealResponse initialDealResponse = blackjackController.initialDeal();
        consoleView.printInitialDealResult(initialDealResponse);
    }

    public void hitPlayer(String playerName, boolean isRequestHit) {
        try {
            PlayerHitResponse playerHitResponse = blackjackController.hitPlayer(playerName, isRequestHit);
            consoleView.printPlayerHitResult(playerHitResponse);
        } catch (IllegalStateException e) {
            consoleView.printBustMessage();
            throw new IllegalStateException();
        }
    }

    public List<String> findAllPlayerNames() {
        return blackjackController.findAllPlayerNames();
    }

    public boolean requestHitDecision(String playerName) {
        return consoleView.requestHitDecision(playerName);
    }

    public void drawDealer() {
        boolean isDealerDrawing = blackjackController.drawDealer();
        consoleView.printDealerDrawResult(isDealerDrawing);
    }

    public void showCardsResult() {
        List<CardsResultResponse> cardsResultResponses = blackjackController.extractAllCardsResult();
        consoleView.printCardsResults(cardsResultResponses);
    }

    public void showAllProfit() {
        List<ProfitResultResponse> profitResultResponses = blackjackController.calculateAllProfitResult();
        consoleView.printProfitResults(profitResultResponses);
    }
}
