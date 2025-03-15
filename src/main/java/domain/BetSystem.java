package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetSystem {

    private final Map<Gamer, Integer> beforeBetRecord;
    private final Map<Gamer, Integer> afterBetRecord;

    public BetSystem() {
        this.beforeBetRecord = new LinkedHashMap<>();
        this.afterBetRecord = new LinkedHashMap<>();
        this.afterBetRecord.put(new Dealer(), 0);
    }

    public void betting(final Player player, final int betAmount) {
        validateBetAmount(betAmount);
        beforeBetRecord.put(player, betAmount);
        afterBetRecord.put(player, 0);
    }

    private void validateBetAmount(final int betAmount) {
        if (betAmount < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0원 이상이어야 합니다.");
        }
    }

    public Map<Gamer, Integer> calculateProfit(final Dealer dealer, final List<Player> players) {
        for (Player player : players) {
            MatchResult dealerMatchResult = dealer.getMatchResult(player);
            if (dealerMatchResult.equals(MatchResult.LOSE)) {
                int betAmount = beforeBetRecord.get(player);

                Integer dealerBetAmount = afterBetRecord.get(dealer);

                dealerBetAmount = dealerBetAmount - betAmount;

                afterBetRecord.put(player, betAmount);
                afterBetRecord.put(dealer, dealerBetAmount);
            }

            if (dealerMatchResult.equals(MatchResult.WIN)) {

                int betAmount = beforeBetRecord.get(player);

                Integer dealerBetAmount = afterBetRecord.get(dealer);
                dealerBetAmount = dealerBetAmount + betAmount;

                afterBetRecord.put(dealer, dealerBetAmount);
                afterBetRecord.put(player, -betAmount);
            }
        }

        return afterBetRecord;
    }
}
