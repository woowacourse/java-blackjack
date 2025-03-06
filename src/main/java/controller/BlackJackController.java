package controller;

import domain.BlackJackGame;
import domain.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJackGame blackJackGame;

    public BlackJackController(InputView inputView, OutputView outputView, BlackJackGame blackJackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackGame = blackJackGame;
    }

    public void run() {
        List<String> playerNames = inputView.readPlayerNames();
        List<Player> players = blackJackGame.createPlayers(playerNames);
        outputView.printInitialCards(players, blackJackGame.getDealer().retrieveFirstCard());
    }
}
