package blackjack.controller;

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
