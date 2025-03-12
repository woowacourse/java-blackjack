package blackjack.controller;

import blackjack.domain.gamer.Player;
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
        for (var player : blackjackService.getPlayers()) {
            drawCardsFor(player);
        }
    }

    private void drawCardsFor(Player player) {
        while (blackjackService.canReceiveAdditionalCards(player)
            && InputView.readAdditionalCardSelection(player.getName()).selection()) {
            blackjackService.drawCardFor(player);
            OutputView.printAdditionalCard(blackjackService.getPlayerCards(player));
        }
        if (!blackjackService.canReceiveAdditionalCards(player)) {
            OutputView.printBustNotice(player.getName());
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
