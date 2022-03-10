package blackjack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputNames());
        List<Player> players = blackjackGame.getPlayers();
        OutputView.printPlayersDefaultCard(players);

        for (Player player : players) {
            hit(blackjackGame, player);
        }

        if (blackjackGame.isDealerReceiveOneMoreCard()) {
            OutputView.printReceivingMoreCardOfDealer();
        }

        OutputView.printCardResult(getCardResult(players));
        OutputView.printGameResult(blackjackGame.getGameResult());
    }

    private static Map<Player, Integer> getCardResult(List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(player -> player, Player::countCards));
    }

    private static void hit(BlackjackGame blackjackGame, Player player) {
        String command = InputView.inputCommand(player);
        while (Command.find(command) == Command.YES) {
            blackjackGame.receiveOneMoreCard(player);
            OutputView.printPlayerCards(player);
            command = InputView.inputCommand(player);
        }
    }
}
