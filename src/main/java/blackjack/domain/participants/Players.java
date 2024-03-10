package blackjack.domain.participants;


import blackjack.domain.deck.Card;
import blackjack.domain.deck.Hands;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {

    public static final int MAX_SCORE = 21;

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Map<Player, Boolean> calculateWinOrLose(int dealerScore) {
        Map<Player, Boolean> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.isWin(dealerScore)));
        return result;
    }

    public void receiveOnePlayerCard(Card card, int playerIndex) {
        players.get(playerIndex).receiveCard(card);
    }

    public void receiveOnePlayerHands(Hands hands, int playerIndex) {
        players.get(playerIndex).receiveHands(hands);
    }

    public boolean isOnePlayerNotOver(int playerIndex) {
        return players.get(playerIndex).isNotOver(MAX_SCORE);
    }

    public int size() {
        return players.size();
    }

    public Name getOnePlayerName(int playerIndex) {
        return players.get(playerIndex).getName();
    }

    public Player getOnePlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
