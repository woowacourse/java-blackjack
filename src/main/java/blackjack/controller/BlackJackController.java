package blackjack.controller;

import blackjack.domain.player.BetMoney;
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
        initSetting();
        play();
        result();
        summary();
    }

    private void initSetting() {
        blackJackService.initChallengers(requestNames());
        blackJackService.initDealer();
        challengerBetting();
        OutputView.printInitSetting(blackJackService.getChallengers());
        OutputView.printInitCards(blackJackService.getDealer(), blackJackService.getChallengers());
    }

    private List<String> requestNames() {
        return InputView.getNames();
    }

    private void challengerBetting() {
        Challengers challengers = blackJackService.getChallengers();
        for (Challenger challenger : challengers.toList()) {
            challenger.betting(new BetMoney(InputView.getBetMoney(challenger)));
        }
    }


    private void play() {
        Challengers challengers = blackJackService.getChallengers();
        for (Challenger challenger : challengers.toList()) {
            receiveChallengerMoreCard(challenger);
        }
        receiveDealerMoreCard(blackJackService.getDealer());
    }

    private void receiveChallengerMoreCard(final Challenger challenger) {
        while (!challenger.isBust() && InputView.wantMoreCard(challenger)) {
            blackJackService.receiveMoreCard(challenger);
            OutputView.printPlayerCards(challenger);
        }
    }

    private void receiveDealerMoreCard(final Dealer dealer) {
        while (!dealer.isBust() && dealer.canDraw()) {
            blackJackService.receiveMoreCard(dealer);
            OutputView.printDealerReceiveMessage();
            OutputView.printNewLine();
        }
    }

    private void result() {
        OutputView.printResult(blackJackService.getPlayers());
    }

    private void summary() {
        ResultStatistics resultStatistics = new ResultStatistics(blackJackService.getChallengers(), blackJackService.getDealer());
        OutputView.printSummary(resultStatistics, blackJackService.getChallengers());
    }
}
