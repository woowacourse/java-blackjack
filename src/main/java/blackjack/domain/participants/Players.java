package blackjack.domain.participants;


import static blackjack.domain.participants.Player.BLACKJACK_SCORE;

import blackjack.domain.cards.Card;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Map<Player, Result> calculateResult(int dealerScore, boolean isDealerBlackjack) {
        Map<Player, Result> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.checkResult(dealerScore, isDealerBlackjack)));
        return result;
    }

    public void betPlayerMoney(GamblingMoney gamblingMoney, int playerIndex) {
        players.get(playerIndex).betMoney(gamblingMoney);
    }

    public void receivePlayerCard(Card card, int playerIndex) {
        players.get(playerIndex).receiveCard(card);
    }

    public boolean isPlayerNotOver(int playerIndex) {
        return players.get(playerIndex).isNotOver(BLACKJACK_SCORE);
    }

    public int size() {
        return players.size();
    }

    public Name getPlayerName(int playerIndex) {
        return players.get(playerIndex).getName();
    }

    public GamblingMoney getPlayerGamblingMoney(int playerIndex) {
        return players.get(playerIndex).getGamblingMoney();
    }

    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
