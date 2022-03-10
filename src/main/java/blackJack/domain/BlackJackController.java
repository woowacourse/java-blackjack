package blackJack.domain;

import java.util.List;
import java.util.stream.Collectors;

import blackJack.domain.participant.Player;
import blackJack.domain.result.YesOrNo;
import blackJack.domain.view.InputView;

public class BlackJackController {

    private List<Player> getPlayerNames() {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            return getPlayerNames();
        }
    }

    private void doPlayerGame(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            doEachPlayerTurn(blackJackGame, player);
        }
    }

    private void doEachPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while (blackJackGame.hasNextTurn(player) || getOneMoreCard(player)) {
            blackJackGame.distributeCard(player, 1);
        }
    }

    private boolean getOneMoreCard(Player player) {
        try {
            String choice = InputView.inputOneMoreCard(player.getName());
            return YesOrNo.YES == YesOrNo.find(choice);
        } catch (IllegalArgumentException e) {
            return getOneMoreCard(player);
        }
    }
}
