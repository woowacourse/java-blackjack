package controller;

import domain.GameManager;
import domain.Player;
import domain.dto.GameInitialInfoDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class GameController {

    private GameManager manager;
    private InputView inputView;
    private OutputView outputView;

    public GameController(GameManager manager, InputView inputView, OutputView outputView) {
        this.manager = manager;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        registerPlayer();
        initGame();
        playPlayerTurn();
        playDealerTurn();
        outputView.printScoreResults(manager.getScoreResults());
        outputView.printFinalResult(manager.getFinalResult());
    }

    private void playDealerTurn() {
        while (manager.canReceiveCard()) {
            manager.drawDealerCard();
            outputView.printDealerTurn();
        }
    }

    private void playPlayerTurn() {
        for (Player player : manager.getPlayerSequence()) {
            while (manager.canReceiveCard(player) && isStand(player)) {
                List<String> playerHand = manager.drawPlayerCard(player);
                outputView.printHand(playerHand, player.getName());
            }
        }
    }

    private void registerPlayer() {
        List<String> playerNames = inputView.readPlayerName();
        for (String playerName : playerNames) {
            manager.addPlayer(playerName);
        }
    }

    private void initGame() {
        manager.startGame();

        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();
        outputView.printInitialInfo(initialInfo);
    }

    private boolean isStand(Player player) {
        return !inputView.readCommand(player.getName()).equals("n");
    }
}
