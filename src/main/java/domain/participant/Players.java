package domain.participant;

import domain.betting.BettingAmount;
import domain.betting.BettingAmounts;
import domain.betting.CalculateProfit;
import domain.betting.Revenue;
import domain.game.GameResult;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Players {
    private static final int MAX_PLAYER = 8;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayers(players);
        this.players = new ArrayList<>(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(player -> player.getName().getName())
                .toList();
    }

    public BettingAmounts createBettingAmounts(BigDecimal amount) {
        Map<Name, BettingAmount> bettingAmounts = new HashMap<>();
        players.forEach(player -> {
            bettingAmounts.put(player.getName(), new BettingAmount(amount));
        });
        return new BettingAmounts(bettingAmounts);
    }

    public Map<String, GameResult> judgeResultsAgainst(Dealer dealer) {
        Map<String, GameResult> gameResult = new HashMap<>();
        players.forEach(player -> {
            GameResult result = player.judgeResult(dealer);
            gameResult.put(player.getName().getName(), result);
        });
        return gameResult;
    }

    public LinkedHashMap<Name, Revenue> calculateProfitsAgainst(Dealer dealer, CalculateProfit calculateProfit) {
        LinkedHashMap<Name, Revenue> finalRevenues = new LinkedHashMap<>();

        players.forEach(player -> {
            GameResult result = player.judgeResult(dealer);
            Revenue revenue = calculateProfit.calculate(player.getName(), result);
            finalRevenues.put(player.getName(), revenue);
        });
        return finalRevenues;
    }

    public static void validatePlayers(List<Player> players) {
        validateMinimumPlayers(players);
        validateMaximumPlayers(players);
        validateDuplicateName(players);
    }

    private static void validateMaximumPlayers(List<Player> players) {
        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private static void validateMinimumPlayers(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상이어야 합니다.");
        }
    }

    private static void validateDuplicateName(List<Player> players) {
        Set<Name> uniqueNames = new HashSet<>();
        for (Player player : players) {
            uniqueNames.add(player.getName());
        }
        if (players.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("중복된 참가자 이름이 있습니다!");
        }
    }
}
