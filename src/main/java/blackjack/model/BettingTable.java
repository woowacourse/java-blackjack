package blackjack.model;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {

    private final Map<Player, Integer> betting;

    public BettingTable() {
        this.betting = new LinkedHashMap<>();
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
}
