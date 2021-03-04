package blackjack.controller;

import blackjack.domain.GameTable;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public BlackJackController() {
    }

    public void run() {
        OutputView.printPlayersGuideMessage();
        List<Player> players = makePlayers(InputView.inputPlayers());
        GameTable gameTable = new GameTable(players);
        gameTable.playGame();
    }

    private List<Player> makePlayers(List<String> inputPlayers) {
        return inputPlayers.stream().map(Player::new).collect(Collectors.toList());
    }

}
