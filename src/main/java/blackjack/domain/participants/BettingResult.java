package blackjack.domain.participants;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResult {
    private final Map<Player, Profit> bettingResult;

    public BettingResult() {
        this.bettingResult = new LinkedHashMap<>();
    }

    public void bet(Player player, Profit profit) {
        bettingResult.put(player, profit);
    }

    public void calculateProfit(Player player, State state) { // TODO 메서드 길이 줄이기
        if (!bettingResult.containsKey(player)) {
            throw new IllegalArgumentException("해당 유저는 베팅하지 않았습니다.");
        }
        if (state == State.WIN && player.isBlackjack()) {
            bettingResult.put(player, bettingResult.get(player).multiple(1.5));
            return;
        }
        if (state == State.WIN) {
            bettingResult.put(player, bettingResult.get(player));
            return;
        }
        if (state == State.LOSE) {
            bettingResult.put(player, bettingResult.get(player).inverse());
            return;
        }
        bettingResult.put(player, new Profit(0));
    }

    public int getDealerProfit() {
        if (bettingResult.isEmpty()) {
            throw new IllegalArgumentException("베팅을 하지 않았습니다.");
        }
        return -1 * bettingResult.values().stream()
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
