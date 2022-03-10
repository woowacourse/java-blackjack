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
            // todo: 더 받을지 입력 받기
            OutputView.printPlayerCards(player);
        }
        if (blackjackGame.isDealerReceiveOneMoreCard()) {
            OutputView.printReceivingMoreCardOfDealer();
        }

        Map<Player, Integer> cardResult = players.stream()
                .collect(Collectors.toMap(player -> player, Player::countCards));

        OutputView.printCardResult(cardResult);
        OutputView.printGameResult(blackjackGame.getGameResult());
    }
}
