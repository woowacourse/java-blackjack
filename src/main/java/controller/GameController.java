package controller;

import domain.GameManager;
import view.InputView;
import view.OutputView;

import java.util.List;

public class GameController {

    private final GameManager manager;
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(GameManager manager, InputView inputView, OutputView outputView) {
        this.manager = manager;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playerNames = registerPlayer();
        initGame();
        playPlayerTurn(playerNames);
        playDealerTurn();
        outputView.printScoreResults(manager.getScoreResults());
        outputView.printFinalResult(manager.getFinalResult());
    }

    private List<String> registerPlayer() {
        List<String> playerNames = inputView.readPlayerName();

        for (String playerName : playerNames) {
            String betAmount = inputView.readBetAmount(playerName);
            manager.registerPlayer(playerName, betAmount);
        }

        return playerNames;
    }

    private void initGame() {
        manager.startGame();
        outputView.printInitialInfo(manager.getInitialInfo());
    }

    private void playPlayerTurn(List<String> playerNames) {
        for (String playerName : playerNames) {
            while (manager.canPlayerReceiveCard(playerName) && isStand(playerName)) {
                List<String> playerHand = manager.drawPlayerCard(playerName);
                outputView.printHand(playerHand, playerName);
            }
        }
    }

    private void playDealerTurn() {
        while (manager.canDealerReceiveCard()) {
            manager.drawDealerCard();
            outputView.printDealerTurn();
        }
    }

    private boolean isStand(String player) {
        return !inputView.readCommand(player).equals("n");
    }
}
