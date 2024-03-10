package controller;

import domain.BlackJackGame;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameManager {
    private final InputView inputView;
    private final OutputView outputView;

    public GameManager(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = start();
        blackJackGame.rotate();
        blackJackGame.finish();
    }

    private BlackJackGame start() {
        BlackJackGame blackJackGame = BlackJackGame.from(inputView.enterPlayerNames());
        blackJackGame.initialize();
        initializeGame();
        List<String> names = inputView.enterPlayerNames();
        BlackJackGame blackJackGame = BlackJackGame.from(names);
        outputView.printAfterStartGame(blackJackGame.initiateGameCondition());
        return blackJackGame;
    }

    public void run() {
        BlackJackGame blackJackGame = start();
        startRound(blackJackGame);
        blackJackGame.finish(outputView);
    }


    private void startRound(final BlackJackGame blackJackGame) {
        List<String> names = blackJackGame.getPlayerNames();
        for (String name : names) {
            blackJackGame.giveCardToPlayer(name, outputView, inputView);
        }

        blackJackGame.giveCardsToDealer(outputView);
    }
}
