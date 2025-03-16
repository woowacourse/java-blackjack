package bet;

import java.util.LinkedHashMap;
import java.util.Map;
import player.Player;

public class BetManager {
    private final Map<Player, Bet> wager;

    public BetManager() {
        wager = new LinkedHashMap<>();
    }

    public void addBet(Player player, int amount) {
        wager.put(player, new Bet(amount));
    }

    public void calculateBettingResult(boolean isBlackjack, boolean isWinner, boolean isDraw) {
        wager.forEach((key, value) -> value.calculateBettingResult(isBlackjack, isWinner, isDraw));
    }

    public Bet getBet(Player player) {
        if (!wager.containsKey(player)) {
            throw new IllegalStateException("플레이어가 존재하지 않을 경우의 수가 없습니다.");
        }

        return wager.get(player);
    }

    public int calculateDealerBettingResult() {
        return - wager.values().stream()
                .mapToInt(Bet::getAmount)
                .sum();
    }

    public int getBetAmount(Player player) {
        Bet bet = getBet(player);
        return bet.getAmount();
    }

    public Map<Player, Bet> getWager() {
        return wager;
    }
}
