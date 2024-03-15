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

    public Map<Player, Victory> calculateVictory(int dealerScore, boolean isDealerBlackjack) {
        Map<Player, Victory> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.checkVictory(dealerScore, isDealerBlackjack)));
        return result;
    }

    public void betOnePlayerMoney(GamblingMoney gamblingMoney, int playerIndex) {
        players.get(playerIndex).betMoney(gamblingMoney);
    }

    public void receiveOnePlayerCard(Card card, int playerIndex) {
        players.get(playerIndex).receiveCard(card);
    }

    public boolean isOnePlayerNotOver(int playerIndex) {
        return players.get(playerIndex).isNotOver(BLACKJACK_SCORE);
    }

    public int size() {
        return players.size();
    }

    public Name getOnePlayerName(int playerIndex) {
        return players.get(playerIndex).getName();
    }

    public GamblingMoney getOnePlayerMoney(int playerIndex) {
        return players.get(playerIndex).getMoney();
    }

    public Player getOnePlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public List<Player> getPlayersValue() {
        return players;
    }
}
