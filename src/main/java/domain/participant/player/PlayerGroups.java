package domain.participant.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class PlayerGroups {
    private static final int PLAYER_NUMBER_LIMIT = 4;
    private final List<Player> players;
    private int playIndex = 0;

    public PlayerGroups(List<Player> players) {
        validatePlayerNum(players);
        this.players = new ArrayList<>(players);
    }

    public void onePlayerDrawCard(Card card) {
        players.get(playIndex).drawCard(card);
    }

    public Player getCurrentPlayer() {
        return players.get(playIndex);
    }

    public List<String> getCurrentPlayerCards() {
        return players.get(playIndex).getCards();
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
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

    private void validatePlayerNum(List<Player> players) {
        if (players.size() > PLAYER_NUMBER_LIMIT) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수가 " + players.size() + "명으로 정원인 " + PLAYER_NUMBER_LIMIT + "명을 초과합니다.");
        }
    }
}
