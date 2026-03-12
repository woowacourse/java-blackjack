package blackjack.domain.participant;

import blackjack.domain.result.GameJudge;
import blackjack.domain.result.GameOutcome;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameSummary;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> allPlayers) {
        validateDuplicate(allPlayers);
        validatePlayerCount(allPlayers);
        this.players = allPlayers;
    }

    public List<Player> all() {
        return players;
    }

    public List<GameSummary> calculateGameSummaries(Dealer dealer) {
        List<GameSummary> gameSummaries = new ArrayList<>();
        gameSummaries.add(GameSummary.from(dealer));
        players.forEach(player -> gameSummaries.add(GameSummary.from(player)));
        return gameSummaries;
    }

    public List<GameResult> calculateGameResults(Dealer dealer) {
        GameJudge gameJudge = new GameJudge();
        List<GameResult> gameResults = new ArrayList<>();
        int totalPlayerProfit = 0;

        for (Player player : players) {
            GameOutcome outcome = gameJudge.judge(player, dealer);
            int playerProfit = player.getBet().calculateProfit(outcome.getPayoutRate());
            totalPlayerProfit += playerProfit;
            gameResults.add(GameResult.from(player, playerProfit));
        }

        gameResults.addFirst(GameResult.from(dealer, -totalPlayerProfit));
        return gameResults;
    }

    private void validateDuplicate(List<Player> allPlayers) {
        long unique = allPlayers.stream().map(Player::getName).distinct().count();
        if (unique != allPlayers.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerCount(List<Player> allPlayers) {
        if (allPlayers.size() > 7) {
            throw new IllegalArgumentException("플레이어의 최대 인원은 7명입니다.");
        }
    }
}
