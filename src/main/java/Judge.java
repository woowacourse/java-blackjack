import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Judge {

    private final Map<Player, WinState> result;

    public Judge() {
        this.result = new LinkedHashMap<>();
    }

    public void decidePlayerResult(Player player, Dealer dealer) {
        if (!player.isNotBust()) {
            result.put(player, WinState.LOSE);
            return;
        }
        if (!dealer.isNotBust()) {
            result.put(player, WinState.WIN);
            return;
        }
        result.put(player, judgePlayerWinState(player, dealer));
    }

    private WinState judgePlayerWinState(Player player, Dealer dealer) {
        int playerScore = player.finalizeCardsScore();
        int dealerScore = dealer.finalizeCardsScore();

        if (playerScore > dealerScore) {
            return WinState.WIN;
        }
        if (playerScore < dealerScore) {
            return WinState.LOSE;
        }
        return WinState.DRAW;
    }

    public Map<WinState, Integer> decideDealerResult() {
        Map<WinState, Integer> dealerResult = new LinkedHashMap<>();
        dealerResult.put(WinState.WIN, countState(WinState.LOSE));
        dealerResult.put(WinState.DRAW, countState(WinState.DRAW));
        dealerResult.put(WinState.LOSE, countState(WinState.WIN));
        return dealerResult;
    }

    private int countState(WinState winState) {
        return (int) result.values().stream()
                .filter(value -> value.equals(winState))
                .count();
    }

    public Map<Player, WinState> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
