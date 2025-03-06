package blackjack.controller;

import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private final BlackjackService blackjackService = new BlackjackService();

    public void run() {
        setPlayers();
        drawPlayerCards();
        drawDealerCards();
        printResult();
    }

    private void setPlayers() {
        blackjackService.setPlayer(InputView.readNames());
        OutputView.printStartingCards(blackjackService.drawStartingCards());
    }

    private void drawPlayerCards() {
        blackjackService.drawCards(
            InputView::readAdditionalCardSelection,
            OutputView::printAdditionalCard,
            OutputView::printBustNotice);
    }

    private void drawDealerCards() {
        while (blackjackService.dealerCanReceiveAdditionalCards()) {
            blackjackService.drawCardForDealer();
            OutputView.printDealerDrawNotice();
        }
    }

    private void printResult() {
        OutputView.printRoundResult(blackjackService.getRoundResults());
        OutputView.printFinalResult(blackjackService.getFinalResult());
    }
}
