package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> allPlayers) {
        validateDuplicate(allPlayers);
        this.players = allPlayers;
    }

    public List<Player> all() {
        return players;
    }

    public List<GameSummary> calculateGameResult(Dealer dealer) {

        List<GameSummary> gameSummaries = new ArrayList<>();

        GameSummary dealerSummary = GameSummary.from(dealer);
        gameSummaries.add(dealerSummary);

        for (Player player : players) {
            GameOutcome result = GameOutcome.judge(player, dealer);

            player.mark(result);
            dealer.addResult(result.reverse());

            gameSummaries.add(GameSummary.from(player));
        }

        return gameSummaries;
    }

    public List<GameResult> calculateProfit(Dealer dealer) {
        List<GameResult> gameResults = new ArrayList<>();
        int totalPlayerProfit = 0;

        for (Player player : players) {
            int playerProfit = player.getBet().calculateProfit(player.getGameResult().getPayoutRate());
            totalPlayerProfit += playerProfit;
            gameResults.add(GameResult.from(player, playerProfit));
        }

        int dealerProfit = -totalPlayerProfit;
        gameResults.addFirst(GameResult.from(dealer, dealerProfit));

        return gameResults;
    }

    private void validateDuplicate(List<Player> allPlayers) {
        long unique = allPlayers.stream().map(Player::getName).distinct().count();
        if (unique != allPlayers.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }
}
