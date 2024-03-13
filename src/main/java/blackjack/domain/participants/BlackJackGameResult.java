package blackjack.domain.participants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameResult {
    private final Map<Player, State> gameResult;

    public BlackJackGameResult(List<Player> players, Dealer dealer) {
        this.gameResult = new LinkedHashMap<>();
        players.forEach(player -> gameResult.put(player, calculateState(player, dealer)));
    }

    private State calculateState(Player player, Dealer dealer) {
        if (isWin(player, dealer)) {
            return State.WIN;
        }
        if (isTie(player, dealer)) {
            return State.TIE;
        }
        return State.LOSE;
    }

    private boolean isWin(Player player, Dealer dealer) {
        if (player.isBurst()) {
            return false;
        }
        if (dealer.isBurst()) {
            return true;
        }
        return player.calculateScore() > dealer.calculateScore();
    }

    private boolean isTie(Player player, Dealer dealer) {
        if (player.isBurst() || dealer.isBurst()) {
            return false;
        }
        return player.isBlackjack() && dealer.isBlackjack();
    }

    public int size() {
        return gameResult.size();
    }

    public State getState(Player player) {
        return gameResult.get(player);
    }

    public Map<Player, State> getGameResult() {
        return new HashMap<>(gameResult);
    }
}
