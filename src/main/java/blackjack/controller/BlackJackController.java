package blackjack.controller;

import blackjack.model.Command;
import blackjack.model.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Player> players = inputView.readPlayers();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            Command command = inputView.readCommand(player);
        }
    }
}
