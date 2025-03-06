package contoller;

import domain.Deck;
import domain.GameManager;
import view.InputView;

import java.util.List;

public class BlackjackController {

    private GameManager gameManager;

    public BlackjackController() {
    }

    public void run() {
        List<String> playerNames = InputView.readPlayerNames();
        this.gameManager = new GameManager(playerNames, new Deck());

//        gameManager.findInitialCards();
    }
}
