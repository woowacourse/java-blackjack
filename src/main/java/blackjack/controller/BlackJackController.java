package blackjack.controller;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Challengers;
import blackjack.domain.player.Dealer;
import blackjack.domain.result.ResultStatistics;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {
    private final BlackJackService blackJackService;

    public BlackJackController(final BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {
        initBlackJackGame();
        distributeCards();
        cardStatus();
        gameResult();
    }

    private void initBlackJackGame() {
        try {
            blackJackService.initChallengers(requestNames());
            blackJackService.initDealer();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initBlackJackGame();
            return;
        }
        OutputView.printInitSetting(blackJackService.getPlayers());
        OutputView.printInitCards(blackJackService.getDealer(), blackJackService.getChallengers());
    }

    private void distributeCards() {
        if (blackJackService.isDealerBlackJack()) {
            return;
        }

        Challengers challengers = blackJackService.getChallengers();
        for (Challenger challenger : challengers.getChallengersAsList()) {
            receiveChallengerMoreCard(challenger);
        }
        receiveDealerMoreCard(blackJackService.getDealer());
    }

    private void cardStatus() {
        OutputView.printResult(blackJackService.getPlayers());
    }

    private void gameResult() {
        ResultStatistics resultStatistics = new ResultStatistics(blackJackService.getChallengers(), blackJackService.getDealer());
        OutputView.printSummary(resultStatistics);
    }

    private List<String> requestNames() {
        return InputView.getNames();
    }

    private void receiveChallengerMoreCard(final Challenger challenger) {
        while (!challenger.isBust() && InputView.wantMoreCard(challenger)) {
            blackJackService.receiveMoreCard(challenger);
            OutputView.printPlayerCards(challenger);
        }
    }

    private void receiveDealerMoreCard(final Dealer dealer) {
        while (!dealer.isBust() && !dealer.scoreGreaterThanSixteen()) {
            blackJackService.receiveMoreCard(dealer);
            OutputView.printDealerReceiveMessage();
        }
    }
}
