package controller;

import domain.BlackJack;
import domain.strategy.RandomBasedIndexGenerator;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    public void startGame() {
        OutputView.printParticipantNamesGuide();
        String participantNames = InputView.repeat(InputView::inputParticipantNames);

        BlackJack blackJack = new BlackJack(participantNames, new RandomBasedIndexGenerator());
        blackJack.startGame();
    }
}
