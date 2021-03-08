package blackjack.controller;

import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public BlackJackController() {
    }

    public void run() {
        try {
            OutputView.printPlayersGuideMessage();
            Players players = makePlayers(InputView.inputPlayers());

            GameTableController gameTableController = new GameTableController(players);
            gameTableController.playGame();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
        }
    }

    private Players makePlayers(List<String> inputPlayers) {
        return new Players(inputPlayers.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }
}
