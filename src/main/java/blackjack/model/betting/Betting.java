package blackjack.model.betting;

import blackjack.model.MatchResult;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import java.util.HashMap;
import java.util.Map;

public class Betting {

    private final Map<Participant, BetAmount> bets;

    public Betting() {
        this.bets = new HashMap<>();
    }

    public void placeABet(Players players, Player player, int money) {
        if (players.isNotContains(player)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
        }
        bets.put(player, new BetAmount(money));
    }

    public Profit cashOut(Player player, MatchResult matchResult) {
        BetAmount betAmount = bets.get(player);
        return Profit.of(betAmount, matchResult);
    }

    public Map<Participant, BetAmount> getBets() {
        return bets;
    }
}
