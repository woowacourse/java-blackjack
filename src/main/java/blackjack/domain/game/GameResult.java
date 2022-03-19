package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    public static final int BLACKJACK_VALUE = 21;
    protected static final int BLACKJACK_COUNT = 2;

    private final Map<Player, Long> bettingResult = new LinkedHashMap<>();

    public GameResult(Participants participants) {
        initGameResult(participants.getPlayers(), participants.getDealer());
    }

    private void initGameResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            bettingResult.put(player, MatchResult.calculateProfit(player, dealer));
        }
    }

    public long getDealerProfit() {
        return bettingResult.values()
                .stream()
                .mapToLong(value -> -value)
                .sum();
    }

    public Long getBettingResult(Player player) {
        return bettingResult.get(player);
    }

    public Map<Player, Long> getBettingResult() {
        return Collections.unmodifiableMap(bettingResult);
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "bettingResult=" + bettingResult +
                '}';
    }
}
