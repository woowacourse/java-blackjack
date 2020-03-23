package domain;

import domain.player.Dealer;
import domain.player.Users;
import domain.player.Player;
import domain.profit.ProfitFactory;
import domain.profit.ProfitStrategy;

import java.util.LinkedHashMap;
import java.util.Map;

public class WinningResult {
    private static final double DEALER_PROFIT_RATE = -1;

    private Map<String, Double> winningResult;

    private WinningResult(Users users) {
        if (users == null) {
            throw new NullPointerException("결과를 계산할 플레이어가 없습니다.");
        }

        winningResult = new LinkedHashMap<>();

        Dealer dealer = users.getDealer();
        winningResult.put(dealer.getName(), 0d);
        for (Player player : users.getPlayer()) {
            ProfitStrategy profitStrategy = ProfitFactory.create(player, dealer);
            winningResult.put(player.getName(), profitStrategy.getProfit(player.getBettingMoney()));
        }
        winningResult.put(dealer.getName(), calculateDealerProfit());
    }

    private double calculateDealerProfit() {
        double playerProfitSum = winningResult.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        return DEALER_PROFIT_RATE * playerProfitSum;
    }

    public static WinningResult create(Users users) {
        return new WinningResult(users);
    }

    public Map<String, Double> getWinningResult() {
        return winningResult;
    }
}
