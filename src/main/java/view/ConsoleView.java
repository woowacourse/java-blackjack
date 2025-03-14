package view;

import domain.Dealer;
import domain.GameResults;
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

    public void printInitialCards(Dealer dealer, Players players) {
        outputView.printInitialCards(dealer, players);
    }

    public void printDealerDraw(boolean possibleDraw) {
        if (possibleDraw) {
            outputView.printDealerDraw();
            return;
        }
        outputView.printDealerNoDraw();
    }

    public void printCardsResult(Dealer dealer, Players players) {
        outputView.printCardsResult(dealer, players);
    }

    public void printGameResults(GameResults gameResults) {
        outputView.printGameResults(gameResults);
    }

    public AnswerType requestAdditionalCard(Player player) {
        return inputView.requestAdditionalCard(player);
    }

    public void printCurrentCard(Player player) {
        outputView.printCurrentCard(player);
    }

    public void printBustMessage() {
        outputView.printBustMessage();
    }
}
