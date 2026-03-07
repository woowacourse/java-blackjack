package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class PlayerGroups {
    private List<Player> players;
    private int playIndex = 0;

    public PlayerGroups(List<Player> players) {
        validatePlayerNum(players);
        this.players = new ArrayList<>(players);
    }

    public void drawCard(Card card){
        players.get(playIndex).addCard(card);
    }

    public int getPlayerGroupSize() {
        return players.size();
    }

    public boolean hasNextPlayer() {
        if (playIndex > players.size() - 1) {
            playIndex = 0;
            return false;
        }

        return true;
    }

    public Player nextPlayer() {
        return players.get(playIndex++);
    }


    private void validatePlayerNum(List<Player> players){
        if (players.size() > 5) {
            throw new IllegalArgumentException();
        }
    }
}
