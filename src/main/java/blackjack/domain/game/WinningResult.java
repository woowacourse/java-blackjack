package blackjack.domain.game;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class WinningResult {
    private final Map<Player, WinOrLose> gamblerResults = new LinkedHashMap<>();
    private final Cards dealerCards;

    public WinningResult(Cards dealerCards) {
        this.dealerCards = dealerCards;
    }

    public void addGamblerResults(final Player player, WinOrLose winOrLose) {
        gamblerResults.put(player, winOrLose);
    }

    public int countDealerWin() {
        return (int) gamblerResults.keySet().stream()
                .filter(name -> gamblerResults.get(name) == WinOrLose.LOSE)
                .count();
    }

    public int countDealerLose() {
        return (int) gamblerResults.keySet().stream()
                .filter(name -> gamblerResults.get(name) == WinOrLose.WIN)
                .count();
    }

    public int countDealerDraw() {
        return (int) gamblerResults.keySet().stream()
                .filter(name -> gamblerResults.get(name) == WinOrLose.DRAW)
                .count();
    }

    public Set<Player> getGamblerSet() {
        return gamblerResults.keySet();
    }

    public Map<Player, WinOrLose> getGamblerResults(){
        return gamblerResults;
    }

    public Cards getDealerCards() {
        return dealerCards;
    }
}
