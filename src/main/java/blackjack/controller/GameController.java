package blackjack.Controller;

import blackjack.domain.Game;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class GameController {
    public void run() {
        Game game = initializeGame();
        printInitialCardsInfo(game);
    }

    private Game initializeGame() {
        Players players = createPlayers();
        return Game.of(players);
    }

    private Players createPlayers() {
        Map<String, Integer> playerInformation = getPlayerNameAndBattingMoney();
        List<Player> players = playerInformation.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new Players(players);
    }

    private Map<String, Integer> getPlayerNameAndBattingMoney() {
        String inputNames = InputView.inputNames();
        String[] names = inputNames.split(",");

        return Arrays.stream(names)
                .map(String::trim)
                .collect(toMap(name -> name, this::inputBattingMoney));
    }

    private int inputBattingMoney(String name) {
        return InputView.inputBattingMoney(name);
    }

    private void printInitialCardsInfo(Game game) {
        OutputView.printInitialCardsInfo(game.getDealer(), game.getPlayers());
    }
}
