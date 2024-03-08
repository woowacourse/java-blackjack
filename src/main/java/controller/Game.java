package controller;

import java.util.List;
import view.InputView;
import view.OutputView;

public class Game {
    private final InputView inputView;
    private final OutputView outputView;

    public Game(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Round round = start();
        startRound(round);
        round.finish(outputView);
    }

    private Round start() {
        List<String> names = inputView.enterPlayerNames();
        Round round = Round.from(names);
        outputView.printAfterStartGame(round.initiateGameCondition());
        return round;
    }

    private void startRound(final Round round) {
        List<String> names = round.getPlayerNames();
        for (String name : names) {
            round.giveCardToPlayer(name, outputView, inputView);
        }

        round.giveCardsToDealer(outputView);
    }
}
