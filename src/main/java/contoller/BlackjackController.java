package contoller;

import domain.Dealer;
import domain.Deck;
import domain.GameManager;
import domain.Player;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {

    private GameManager gameManager;

    public BlackjackController() {
    }

    public void run() {
        List<String> playerNames = InputView.readPlayerNames();
        this.gameManager = new GameManager(playerNames, new Deck());

        Dealer dealer = gameManager.findDealer();
        List<Player> allPlayers = gameManager.findAllPlayers();
        OutputView.printInitialParticipant(dealer, allPlayers);
    }
}
