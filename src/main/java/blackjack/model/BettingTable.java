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
        players.forEach(player -> betting.put(player, INITIALIZE_BET_AMOUNT));
    }

    public static BettingTable createWithPlayerNames(List<String> playerNames) {
        List<Player> players = createPlayers(playerNames);
        validatePlayers(players);
        return new BettingTable(players);
    }

    private static List<Player> createPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private static void validatePlayers(List<Player> players) {
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

    public void dealInitialHand(Dealer dealer) {
        for (Player player : betting.keySet()) {
            dealer.dealCard(player);
            dealer.dealCard(player);
        }
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);
    }

    public Map<Player, Integer> calculatePayouts(Dealer dealer) {
        Map<Player, Integer> payouts = new LinkedHashMap<>();
        for (Player player : betting.keySet()) {
            MatchResult playerMatchResult = dealer.evaluateFromPlayerPerspective(player);
            int payout = playerMatchResult.applyMultiplier(getBetAmount(player));
            payouts.put(player, payout);
        }
        return payouts;
    }

    public int getBetAmount(Player player) {
        if (!betting.containsKey(player)) {
            throw new IllegalArgumentException("배팅 내역을 찾을 수 없습니다.");
        }
        return betting.get(player);
    }

    public Map<Player, Integer> getBetting() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(betting));
    }

    public List<Player> getParticipatingPlayers() {
        return List.copyOf(betting.keySet());
    }
}
