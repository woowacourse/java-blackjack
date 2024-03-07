package domain.participants;


import domain.deck.Card;
import domain.deck.Deck;
import domain.participants.Name;
import domain.participants.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {

    public static final int MAX_BOUNDARY_SCORE = 21;
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Map<Player, Boolean> calculateResult(int dealerScore) {
        Map<Player, Boolean> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, calculateVictory(player, dealerScore)));
        return result;
    }

    private boolean calculateVictory(Player player, int dealerScore) {
        if (player.calculateScore() > MAX_BOUNDARY_SCORE) {
            return false;
        }
        if (dealerScore > MAX_BOUNDARY_SCORE) {
            return true;
        }
        return dealerScore < player.calculateScore();
    }

    public void receiveOnePlayerCard(Card card, int playerIndex) {
        players.get(playerIndex).receiveCard(card);
    }

    public void receiveOnePlayerDeck(Deck deck, int playerIndex) {
        players.get(playerIndex).receiveDeck(deck);
    }

    public boolean isOnePlayerNotOver(int playerIndex) {
        return players.get(playerIndex).isNotOver(MAX_BOUNDARY_SCORE);
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
