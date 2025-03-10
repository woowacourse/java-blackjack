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
        while (blackjackService.hasMorePlayer()) {
            String nowPlayerName = blackjackService.nextPlayerName();
            drawCardsFor(nowPlayerName);
        }
    }

    private void drawCardsFor(String nowPlayerName) {
        while (blackjackService.canReceiveAdditionalCards(nowPlayerName)
            && InputView.readAdditionalCardSelection(nowPlayerName).selection()) {
            blackjackService.drawCardFor(nowPlayerName);
            OutputView.printAdditionalCard(blackjackService.getPlayerCards(nowPlayerName));
        }
        if (!blackjackService.canReceiveAdditionalCards(nowPlayerName)) {
            OutputView.printBustNotice(nowPlayerName);
        }
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
