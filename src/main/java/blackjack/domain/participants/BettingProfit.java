package blackjack.domain.participants;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingProfit {

    private static final double BLACKJACK_RATIO = 1.5;

    private final Map<Player, Profit> profitResult;

    public BettingProfit() {
        this.profitResult = new LinkedHashMap<>();
    }

    public void calculateProfit(Player player, Result result, Profit bettingProfit) {
        if (result == Result.WIN) {
            handleWin(player, bettingProfit);
            return;
        }
        if (result == Result.LOSE) {
            profitResult.put(player, bettingProfit.inverse());
            return;
        }
        profitResult.put(player, new Profit(0));
    }

    private void handleWin(Player player, Profit bettingProfit) {
        if (player.isBlackjack()) {
            profitResult.put(player, bettingProfit.multiple(BLACKJACK_RATIO));
            return;
        }
        profitResult.put(player, bettingProfit);
    }

    public int getDealerProfit() {
        if (profitResult.isEmpty()) {
            throw new IllegalArgumentException("베팅을 하지 않았습니다.");
        }
        return new Profit(profitResult.values().stream()
                .mapToInt(Profit::getProfit)
                .reduce(Integer::sum)
                .getAsInt())
                .inverse()
                .getProfit();
    }

    public Profit getProfit(Player player) {
        return profitResult.get(player);
    }

    public Map<Player, Profit> getProfitResult() {
        return new LinkedHashMap<>(profitResult);
    }
}
