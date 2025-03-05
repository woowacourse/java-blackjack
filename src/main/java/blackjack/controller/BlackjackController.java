package blackjack.controller;

import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private final BlackjackService blackjackService = new BlackjackService();

    public void run() {
        blackjackService.setPlayer(InputView.readNames());
        OutputView.printStartingCards(blackjackService.drawStartingCards());

        while (blackjackService.hasNextPlayer()) {
            while (InputView.readAdditionalCardSelection(blackjackService.getCurrentPlayerName()).selection()) {
                blackjackService.drawCardForCurrentPlayer();
                OutputView.printAdditionalCard(blackjackService.getNowPlayerCards());
                if (!blackjackService.currentPlayerCanReceiveAdditionalCards()) {
                    OutputView.printBustNotice(blackjackService.getCurrentPlayerName());
                    break;
                }
            }
            blackjackService.nextPlayer();
        }

        while (blackjackService.dealerCanReceiveAdditionalCards()) {
            blackjackService.drawCardForDealer();
            OutputView.printDealerDrawNotice();
        }

        OutputView.printRoundResult(blackjackService.getRoundResults());
    }
}
