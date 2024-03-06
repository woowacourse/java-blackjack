package controller;

import controller.dto.CardStatus;
import domain.Game;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = start();
        startRound(game);
        giveCardToDealer(game);
    }

    private Game start() {
        List<String> names = inputView.enterPlayerNames();
        List<String> savingNames = new ArrayList<>();
        for (String name : names) {
            savingNames.add(name);
        }
        savingNames.add("딜러");

        Game game = new Game(savingNames);
        outputView.printAfterStartGame(game.start());
        return game;
    }

    private void startRound(final Game game) {
        List<String> names = game.getPlayerNames();
        for (String name : names) {
            String command = inputView.decideToGetMoreCard(name);
            CardStatus status = game.getCurrentCardStatus(name);
            while ("y".equals(command)) {
                status = game.pickOneCard(name);
                outputView.printCardStatus(status);
                command = inputView.decideToGetMoreCard(name);
            }
            outputView.printCardStatus(status);
        }
    }

    private void giveCardToDealer(final Game game) {
        int count = game.giveCardsToDealer();
        outputView.printDealerPickMessage(count);
    }
}
