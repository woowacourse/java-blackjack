package blackjack.model;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BettingTable {

    private static final int INITIALIZE_BET_AMOUNT = 0;
    private final Map<Player, Integer> betting;

    public BettingTable(List<Player> players) {
        validatePlayers(players);
        betting = new LinkedHashMap<>();
        for (Player player : players) {
            betting.put(player, INITIALIZE_BET_AMOUNT);
        }
    }

    private void validatePlayers(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("최소 1명 이상의 플레이어가 있어야 합니다.");
        }
        boolean hasDuplicatePlayer = players.size() != new HashSet<>(players).size();
        if (hasDuplicatePlayer) {
            throw new IllegalArgumentException("중복된 이름을 가진 플레이어와 게임을 진행할 수 없습니다.");
        }
    }

    public void bet(Player player, int bettingAmount) {
        betting.put(player, bettingAmount);
    }

    public int getBetAmount(Player player) {
        if (!betting.containsKey(player)) {
            throw new IllegalArgumentException("배팅 내역을 찾을 수 없습니다.");
        }
        return betting.get(player);
    }

    public Map<Player, Integer> calculatePayouts(Dealer dealer) {
        Map<Player, Integer> payouts = new LinkedHashMap<>();
        for (Player player : betting.keySet()) {
            MatchResult matchResult = dealer.evaluateOutcome(player);
            int payout = matchResult.applyMultiplier(getBetAmount(player));
            payouts.put(player, payout);
        }
        return payouts;
    }

    public Map<Player, Integer> getBetting() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(betting));
    }

    public List<Player> getParticipatingPlayers() {
        return List.copyOf(betting.keySet());
    }
}
