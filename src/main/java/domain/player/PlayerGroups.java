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

    public Player getCurrentPlayer() {
        return players.get(playIndex);
    }

    public List<String> getCurrentPlayerCards() {
        return players.get(playIndex).getCards();
    }

    public int getCurrentPlayerCardSum() {
        return players.get(playIndex).getCardSum();
    }

    public Player getDealer() {
        return players.get(0);
    }

    public Map<String, Integer> playersTotalScore() {
        Map<String, Integer> scores = new LinkedHashMap<>();

        for (Player player : players) {
            scores.put(player.getName(), player.getCardSum());
        }

        return scores;
    }

    public Map<String, WinStatus> getGameResult() {
        Player dealer = players.getFirst();
        Map<String, WinStatus> result = new LinkedHashMap<>();

        for (int i = 1; i < players.size(); i++) {
            Player player = players.get(i);
            String playerName = player.getName();

            if(player.isBust()) {
                result.put(playerName, WinStatus.LOSE);
                continue;
            }

            if(dealer.isBust()) {
                result.put(playerName, WinStatus.WIN);
                continue;
            }

            result.put(playerName, player.isWin(dealer.getCardSum()));
        }

        return result;
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
