package blackjack.domain.participants;

import blackjack.domain.Profit;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResult {

    private static final int INVERSE_VALUE = -1;

    private final Map<Player, Profit> bettingResult;

    public BettingResult() {
        this.bettingResult = new LinkedHashMap<>();
    }

    public void bet(Player player, Profit profit) {
        bettingResult.put(player, profit);
    }

    public void calculateProfit(Player player, State state) {
        validatePlayer(player);
        if (state == State.WIN) {
            handleWin(player);
            return;
        }
        if (state == State.LOSE) {
            bettingResult.put(player, bettingResult.get(player).inverse());
            return;
        }
        bettingResult.put(player, new Profit(0));
    }

    private void validatePlayer(Player player) {
        if (!bettingResult.containsKey(player)) {
            throw new IllegalArgumentException("해당 유저는 베팅하지 않았습니다.");
        }
    }

    private void handleWin(Player player) {
        if (player.isBlackjack()) {
            bettingResult.put(player, bettingResult.get(player).multiple(1.5));
            return;
        }
        bettingResult.put(player, bettingResult.get(player));
    }

    public int getDealerProfit() {
        if (bettingResult.isEmpty()) {
            throw new IllegalArgumentException("베팅을 하지 않았습니다.");
        }
        return INVERSE_VALUE * bettingResult.values().stream()
                .map(Profit::getProfit)
                .reduce(Integer::sum)
                .get();
    }

    public Profit getProfit(Player player) {
        return bettingResult.get(player);
    }

    public Map<Player, Profit> getBettingResult() {
        return new LinkedHashMap<>(bettingResult);
    }
}
