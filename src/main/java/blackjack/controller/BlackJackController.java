package blackjack.controller;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Challengers;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
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

    private void summary() {
//        public ResultStatistics(final Challengers challengers, final Dealer dealer)
        ResultStatistics resultStatistics = new ResultStatistics(blackJackService.getChallengers(), blackJackService.getDealer());
        OutputView.printSummary(resultStatistics);
    }

    private void result() {
        OutputView.printResult(blackJackService.getPlayers());
    }

    private void play() {
        Challengers challengers = blackJackService.getChallengers();
        for (Challenger challenger : challengers.getList()) {
            receiveMoreCard(challenger);
        }
        receiveMoreCard(blackJackService.getDealer());
    }

    private void receiveMoreCard(final Player player) {
        if (player instanceof Challenger) {
            receiveChallengerMoreCard((Challenger) player);
        }

        if (player instanceof Dealer){
            receiveDealerMoreCard((Dealer) player);
        }
    }

    private void receiveDealerMoreCard(Dealer dealer) {
        while (!dealer.isBust() && !dealer.isEnoughScore()) {
            blackJackService.receiveMoreCard(dealer);
            OutputView.printDealerReceiveMessage();
            OutputView.printNewLine();
        }
    }

    private void receiveChallengerMoreCard(final Challenger challenger) {
        while (!challenger.isBust() && InputView.wantMoreCard(challenger)) {
            blackJackService.receiveMoreCard(challenger);
            OutputView.printPlayerCards(challenger);
        }
    }

    private void initSetting() {
        blackJackService.initChallengers(requestNames());
        blackJackService.initDealer();
        OutputView.printInitSetting(blackJackService.getPlayers());
        OutputView.printInitCards(blackJackService.getDealer(), blackJackService.getChallengers());
    }

    private List<String> requestNames() {
        return InputView.getNames();
    }
}
