package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

// TODO: 클래스명 변경 고려. 배팅테이블?
public class GameRound {

    private final Dealer dealer;
    private final Map<Player, BettingAmount> bettingAmounts = new LinkedHashMap<>();

    public GameRound(Dealer dealer) {
        this.dealer = dealer;
    }

    public static GameRound start(Dealer dealer) {
        return new GameRound(dealer);
    }

    public void betting(Player player, double money) {
        bettingAmounts.put(player, BettingAmount.of(money));
    }

    public double getEndBettingMoney(Player player) {
        return bettingAmounts.get(player).end();
    }

    public void endGameIfBlackjack(Player player) {
        if (player.isBlackjack()) {
            bettingAmounts.put(player, bettingAmounts.get(player).blackjack());
        }
    }

    public void endGameIfBlackjack(Dealer dealer) {
        if (dealer.isBlackjack()) {
            putBettingAmountsWith(Gamer::isBlackjack, BettingAmount::draw);
        }
    }

    public void endGameIfDealerBust() {
        if (dealer.isBust()) {
            putBettingAmountsWith(player -> !player.isBust(), BettingAmount::win);
        }
    }

    public void computeResult() {
        putBettingAmountsWith(player -> RoundResult.judgeResult(player, dealer) == RoundResult.WIN, BettingAmount::win);
    }

    private void putBettingAmountsWith(Predicate<Player> condition, UnaryOperator<BettingAmount> action) {
        bettingAmounts.keySet().stream()
            .filter(condition)
            .forEach(player -> bettingAmounts.put(player, action.apply(bettingAmounts.get(player))));
    }

    public Map<Gamer, Double> getAllProfit() {
        Map<Gamer, Double> playerResults = bettingAmounts.keySet().stream()
            .collect(Collectors.toMap(player -> player, player -> bettingAmounts.get(player).getProfit()));
        double playersProfitSum = playerResults.values().stream()
            .mapToDouble(Double::doubleValue)
            .sum();

        return collectProfits(playersProfitSum, playerResults);
    }

    private Map<Gamer, Double> collectProfits(double playersProfitSum, Map<Gamer, Double> playerResults) {
        Map<Gamer, Double> allProfit = new LinkedHashMap<>();
        allProfit.put(dealer, -playersProfitSum);
        allProfit.putAll(playerResults);
        return allProfit;
    }
}
