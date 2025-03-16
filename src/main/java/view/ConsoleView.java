package view;

import controller.dto.BettingRequest;
import controller.dto.CardsResultResponse;
import controller.dto.InitialDealResponse;
import controller.dto.PlayerHitResponse;
import controller.dto.ProfitResultResponse;
import domain.Dealer;
import domain.ProfitResults;
import domain.Player;
import domain.Players;
import java.util.List;

public class ConsoleView {

    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public List<String> requestPlayerNames() {
        return inputView.requestPlayerNames();
    }

    public List<BettingRequest> requestBetting() {
        List<String> playerNames = inputView.requestPlayerNames();
        return playerNames.stream()
                .map(playerName -> {
                    int bettingMoney = inputView.requestBettingMoney(playerName);
                    return new BettingRequest(playerName, bettingMoney);
                })
                .toList();
    }

    public void printInitialCards(Dealer dealer, Players players) {
        outputView.printInitialCards(dealer, players);
    }

    public void printInitialDealResult(InitialDealResponse initialDealResponses) {
        outputView.printInitialDealResult(initialDealResponses);
    }

    public void printDealerDrawResult(boolean isDealerDrawing) {
        if (isDealerDrawing) {
            outputView.printDealerDraw();
            return;
        }
        outputView.printDealerNoDraw();
    }

    public void printCardsResult(Dealer dealer, Players players) {
        outputView.printCardsResult(dealer, players);
    }

    public void printGameResults(ProfitResults profitResults) {
        outputView.printGameResults(profitResults);
    }

    public void printCardsResults(List<CardsResultResponse> cardsResultResponses) {
        outputView.printCardsResults(cardsResultResponses);
    }

    public AnswerType requestAdditionalCard(Player player) {
        return inputView.requestAdditionalCard(player);
    }

    public boolean requestHitDecision(String playerName) {
        AnswerType answerType = inputView.requestAdditionalCard(playerName);
        return answerType == AnswerType.YES;
    }

    public void printCurrentCard(Player player) {
        outputView.printCurrentCard(player);
    }

    public void printMessage(String message) {
        outputView.printMessage(message);
    }

    public void printPlayerHitResult(PlayerHitResponse playerHitResponse) {
        outputView.printPlayerHitResult(playerHitResponse);
    }

    public void printProfitResults(List<ProfitResultResponse> profitResultResponses) {
        outputView.printProfitResults(profitResultResponses);
    }
}
