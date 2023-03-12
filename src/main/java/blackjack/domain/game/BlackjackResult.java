package blackjack.domain.game;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.vo.Score;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackResult {
    private final Map<Player, GameResult> blackjackResult;

    public BlackjackResult(Map<Player, GameResult> blackjackResult) {
        this.blackjackResult = blackjackResult;
    }

    public static BlackjackResult of(List<Player> players, Dealer dealer) {
        Map<Player, GameResult> result = players.stream()
                .collect(Collectors.toMap(player -> player,
                        player -> getGameResult(player, dealer),
                        (o1, o2) -> o1,
                        LinkedHashMap::new));
        return new BlackjackResult(result);
    }

    private static GameResult getGameResult(Player player, Dealer dealer) {
        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return compareScore(player.getScore(), dealer.getScore());
    }

    private static GameResult compareScore(Score score, Score dealerScore) {
        if (score.equals(dealerScore)) {
            return GameResult.TIE;
        }
        if (score.isGreaterOrEqualsTo(dealerScore)) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    public int getTieCount() {
        return Collections.frequency(blackjackResult.values(), GameResult.TIE);
    }

    public int getDealerWinCount() {
        return Collections.frequency(blackjackResult.values(), GameResult.LOSE);
    }

    public int getDealerLoseCount() {
        return Collections.frequency(blackjackResult.values(), GameResult.WIN);
    }

    public List<Player> getPlayer() {
        return List.copyOf(blackjackResult.keySet());
    }

    public GameResult get(Player player) {
        return blackjackResult.get(player);
    }
}
