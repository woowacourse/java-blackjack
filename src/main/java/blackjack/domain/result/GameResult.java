package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameResult {

    private final List<PlayerResult> playerResults = new ArrayList<>();

    public GameResult(List<Player> players, Dealer dealer) {
        addResults(players, dealer);
    }

    private void addResults(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            BlackJackResult result = player.match(dealer);
            playerResults.add(new PlayerResult(player.getName(), result));
        }
    }

    public List<PlayerResult> getPlayerResult() {
        return Collections.unmodifiableList(playerResults);
    }
}
