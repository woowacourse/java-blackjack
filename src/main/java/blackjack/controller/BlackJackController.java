package blackjack.controller;

import blackjack.domain.GameTable;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    private static final String PLAYER_COUNT_ERROR_MESSAGE = "플레이어 수는 1명 이상이어야 합니다.";
    private static final int ONE = 1;

    public void run() {
        List<Player> players = new ArrayList<>();
        OutputView.printPlayersGuideMessage();
        try {
            players = makePlayers(InputView.inputPlayers());
            validatePlayersNumber(players);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
        }
        GameTable gameTable = new GameTable(players);
        gameTable.playGame();
    }

    private void validatePlayersNumber(List<Player> players) {
        if (players.size() < ONE) {
            throw new IllegalArgumentException(PLAYER_COUNT_ERROR_MESSAGE);
        }
    }

    private List<Player> makePlayers(List<String> inputPlayers) {
        return inputPlayers.stream().map(Player::new).collect(Collectors.toList());
    }
}
