package blackjack.domain.gamer;

import blackjack.domain.MatchResult;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Players {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 8;
    private static final double BLACKJACK_PROFIT = 1.5;
    private static final int WIN_PROFIT = 1;
    private static final int LOSE_PROFIT = -1;
    private static final int DRAW_PROFIT = 0;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayerCount(players);
        this.players = new ArrayList<>(players);
    }

    private void validatePlayerCount(List<Player> player) {
        if (player.size() > MAX_COUNT || player.size() < MIN_COUNT) {
            throw new IllegalArgumentException("플레이어 인원 수는 1명 이상 8명 이하 입니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<Player, MatchResult> verifyResultByCompareScore(Dealer dealer) {
        Map<Player, MatchResult> result = new LinkedHashMap<>();
        for (Player player : players) {
            MatchResult matchResult = MatchResult
                    .matchPlayerAndDealer(player, dealer);
            result.put(player, matchResult);
        }
        return result;
    }

    public Map<String, Double> calculateProfitByMatchResult(Map<Player, MatchResult> matchResult, Map<String, Double> bettingMoneys) {
        Map<String, Double> result = new LinkedHashMap<>();
        matchResult.forEach((player, matchGame) -> result.put(player.getName(), calculateBettingMoney(matchGame, bettingMoneys.get(player.getName()), player)));

        return result;
    }

    private double calculateBettingMoney(MatchResult matchResult, double money, Player player) {
        if (matchResult.equals(MatchResult.WIN)) {
            return verifyBlackjackOrNot(money, player);
        }
        if (matchResult.equals(MatchResult.LOSE)) {
            return money * LOSE_PROFIT;
        }
        return money * DRAW_PROFIT;
    }

    private double verifyBlackjackOrNot(double money, Player player) {
        if (player.isBlackjack()) {
            return money * BLACKJACK_PROFIT;
        }
        return money * WIN_PROFIT;
    }
}
