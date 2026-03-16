package model.participant;

import constant.ErrorMessage;
import dto.result.ParticipantCurrentHand;
import dto.status.BetPrice;
import dto.status.PlayerStatus;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.card.Card;

public class CurrentPlayers {
    private final Map<String, Player> players = new LinkedHashMap<>();
    private final Map<String, BetPrice> playerBet = new HashMap<>();

    public void addPlayer(Player player) {
        if(players.containsKey(player.getName())) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_NAME.getMessage());
        }

        players.put(player.getName(), player);
        playerBet.put(player.getName(), null);
    }

    public List<String> getPlayerNames() {
        return List.copyOf(players.keySet());
    }

    public void setBet(String playerName, BetPrice bet) {
        playerBet.put(playerName, bet);
    }

    public void drawCard(String playerName, Card card) {
        Player player = getPlayer(playerName);
        player.addCard(card);
    }

    public ParticipantCurrentHand getPlayersCurrentHand(String playerName) {
        Player player = getPlayer(playerName);

        return player.getCurrentHand();
    }

    public List<ParticipantCurrentHand> getPlayersHand() {
        return players.values().stream()
                .map(Participant::getCurrentHand)
                .toList();
    }

    public boolean isPlayerBust(String playerName) {
        Player player = getPlayer(playerName);
        return player.isBust();
    }

    public List<PlayerStatus> getPlayerStatuses() {
        return players.values().stream()
                .map(player -> player.getPlayerStatus(playerBet.get(player.getName()).value()))
                .toList();
    }

    private Player getPlayer(String playerName) {
        if(!players.containsKey(playerName)) {
            throw new IllegalArgumentException(ErrorMessage.NO_PLAYER_NAME.getMessage());
        }

        return players.get(playerName);
    }
}
