package blackjack.controller;

import blackjack.service.BlackjackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private final BlackjackService blackjackService = new BlackjackService();

    public void run() {
        setPlayers();
        // TODO 함수형 인터페이스로 view 로직 분리하여 전달하기
        drawPlayerCards();
        drawDealerCards();
        printResult();
    }

    private void setPlayers() {
        blackjackService.setPlayer(InputView.readNames());
        OutputView.printStartingCards(blackjackService.drawStartingCards());
    }

    private void drawPlayerCards() {
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
