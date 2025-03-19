package view;

import controller.dto.BettingRequest;
import controller.dto.CardsResultResponse;
import controller.dto.InitialDealResponse;
import controller.dto.PlayerHitResponse;
import controller.dto.ProfitResultResponse;
import java.util.List;

public class ConsoleView {

    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
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

    public void printCardsResults(List<CardsResultResponse> cardsResultResponses) {
        outputView.printCardsResults(cardsResultResponses);
    }

    public boolean requestHitDecision(String playerName) {
        AnswerType answerType = inputView.requestAdditionalCard(playerName);
        return answerType == AnswerType.YES;
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
