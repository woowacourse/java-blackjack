package blackjack.domain.participants;

import blackjack.domain.Profit;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingProfit {

    private static final int INVERSE_VALUE = -1;

    private final Map<Player, Profit> profitResult;

    public BettingProfit() {
        this.profitResult = new LinkedHashMap<>();
    }

    public void calculateProfit(Player player, State state, Profit bettingProfit) {
        if (state == State.WIN) {
            handleWin(player, bettingProfit);
            return;
        }
        if (state == State.LOSE) {
            profitResult.put(player, bettingProfit.inverse());
            return;
        }
        profitResult.put(player, new Profit(0));
    }

    private void handleWin(Player player, Profit bettingProfit) {
        if (player.isBlackjack()) {
            profitResult.put(player, bettingProfit.multiple(1.5));
            return;
        }
        profitResult.put(player, bettingProfit);
    }

    public int getDealerProfit() {
        if (profitResult.isEmpty()) {
            throw new IllegalArgumentException("베팅을 하지 않았습니다.");
        }
        return INVERSE_VALUE * profitResult.values().stream()
                .map(Profit::getProfit)
                .reduce(Integer::sum)
                .get();
    }

    public Profit getProfit(Player player) {
        return profitResult.get(player);
    }

    public Map<Player, Profit> getProfitResult() {
        return new LinkedHashMap<>(profitResult);
    }
}
