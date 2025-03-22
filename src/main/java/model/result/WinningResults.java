package model.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public final class WinningResults {
    private final List<PlayerWinningResult> results;

    public static WinningResults of(final Players players, final Dealer dealer) {
        List<PlayerWinningResult> result = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            result.add(PlayerWinningResult.from(dealer, player));
        }
        return new WinningResults(result);
    }

    public WinningResults(final List<PlayerWinningResult> results) {
        this.results = results;
    }

    public Map<GameResult, Integer> decideDealerWinning() {
        Map<GameResult, Integer> dealerResult = new HashMap<>();
        for (PlayerWinningResult result : this.results) {
            GameResult playerGameResult = result.getGameResult();
            updateDealerResult(playerGameResult, dealerResult);
        }
        return dealerResult;
    }

    private void updateDealerResult(final GameResult gameResult, final Map<GameResult, Integer> dealerResult) {
        dealerResult.merge(gameResult.findReverse(), 1, Integer::sum);
    }

    public boolean isLose(final Player player) {
        return findResultByPlayer(player).isLose();
    }

    public boolean isBlackjackWin(final Player player) {
        return findResultByPlayer(player).isBlackjackWin();
    }

    private PlayerWinningResult findResultByPlayer(final Player player) {
        return results.stream()
                .filter(result -> result.getPlayer().equals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 플레이어의 우승결과가 존재하지 않습니다."));
    }

    public Map<Player, GameResult> getResults() {
        Map<Player, GameResult> mappedResult = new HashMap<>();
        for (PlayerWinningResult result : this.results) {
            mappedResult.put(result.getPlayer(), result.getGameResult());
        }
        return Collections.unmodifiableMap(mappedResult);
    }
}
