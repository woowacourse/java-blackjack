package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Referee {

    private enum Judgement {
        BLACKJACK(1.5),
        WIN(1),
        LOSE(-1),
        DRAW(0);

        private final double profitMultiple;

        Judgement(double profitMultiple) {
            this.profitMultiple = profitMultiple;
        }

        public double getProfitMultiple() {
            return profitMultiple;
        }
    }

    private Referee() {
    }

    public static int calculateDealerProfit(List<Integer> playersProfit) {
        int playersProfitSum = playersProfit.stream()
            .reduce(0, Integer::sum);
        return playersProfitSum * -1;
    }

    public static Map<Player, Integer> calculatePlayersProfit(List<Player> players, Dealer dealer) {
        return players.stream()
            .collect(Collectors.toMap(player -> player,
                player -> calculateParticipantProfit(player, dealer),
                (p1, p2) -> p1,
                LinkedHashMap::new));
    }

    private static int calculateParticipantProfit(Player player, Dealer dealer) {
        Judgement judgement = judgePlayer(player, dealer);
        BetMoney betMoney = player.getBetMoney();
        return betMoney.calculateProfit(judgement.getProfitMultiple());
    }

    private static Judgement judgePlayer(Player player, Dealer dealer) {
        if (isPlayerBlackjack(player, dealer)) {
            return Judgement.BLACKJACK;
        }
        if (isPlayerLose(player, dealer)) {
            return Judgement.LOSE;
        }
        if (isPlayerWin(player, dealer)) {
            return Judgement.WIN;
        }
        return Judgement.DRAW;
    }

    private static boolean isPlayerBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean isPlayerLose(Player player, Dealer dealer) {
        return player.isBust() || (player.calculateScore() < dealer.calculateScore() && !dealer.isBust());
    }

    private static boolean isPlayerWin(Player player, Dealer dealer) {
        return dealer.isBust() || player.calculateScore() > dealer.calculateScore();
    }
}
