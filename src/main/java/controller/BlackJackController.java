package controller;

import domain.BlackJack;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final BlackJack blackJack;

    public BlackJackController(BlackJack blackJack) {
        this.blackJack = blackJack;
    }

    public void startGame() {
        OutputView.printParticipantNamesGuide();
        String participantNames = InputView.repeat(InputView::inputParticipantNames);

    }
}
