package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Player> all() {
        return Collections.unmodifiableList(players);
    }

    public List<GameSummary> calculateGameResult(Dealer dealer) {

        List<GameSummary> gameSummaries = new ArrayList<>();

        GameSummary dealerSummary = dealer.toSummary();
        gameSummaries.add(dealerSummary);

        for (Player player : players) {
            GameSummary playerSummary = player.toSummary();
            gameSummaries.add(playerSummary);

            GameResult result = GameResult.judge(playerSummary, dealerSummary);

            player.mark(result);
            dealer.addResult(result.reverse());
        }

        return gameSummaries;
    }
}
