package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, List<String>> playersStatus() {
        Map<String, List<String>> status = new LinkedHashMap<>();

        for (Player player : players) {
            status.put(player.getName(), player.getCards());
        }

        return status;
    }

    // 이번 라운드의, 해당 플레이어 반환
    public Player getCurrentPlayer() {
        return players.get(playIndex);
    }

    // 현재 플레이어의 카드 덱 반환
    public List<String> getCurrentPlayerCards() {
        return players.get(playIndex).getCards();
    }

    // 현재 플레이어의 카드 합산 점수 반환
    public int getCurrentPlayerCardSum() {
        return players.get(playIndex).getCardSum();
    }

    // 딜러 반환
    public Player getDealer() {
        return players.get(0);
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
