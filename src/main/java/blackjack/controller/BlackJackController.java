package blackjack.controller;

import blackjack.domain.BlackJackManager;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        try {
            String input = inputView.readNames();
            List<String> names = InputParser.parseStringToList(input);
            BlackJackManager blackJackManager = BlackJackManager.createByPlayerNames(names);

            // 카드 2장 배부
            blackJackManager.initCardsToParticipants();
            outputView.printStartGame(blackJackManager.getPlayerNames());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
        }
    }
}
