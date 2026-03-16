package model.participant;

import dto.status.BetPrice;
import dto.result.ParticipantCurrentHand;
import dto.status.PlayerName;
import dto.status.PlayerStatus;
import java.util.List;
import model.card.Card;

public class Players {
    private final CurrentPlayers currentPlayers = new CurrentPlayers();
    private final NameValidator nameValidator;

    public Players(NameValidator nameValidator) {
        this.nameValidator = nameValidator;
    }

    public void addPlayer(PlayerName playerName) {
        Player player = new Player(playerName, nameValidator);
        currentPlayers.addPlayer(player);
    }

    public List<String> getPlayerNames() {
        return currentPlayers.getPlayerNames();
    }

    public void setBet(String playerName, BetPrice bet) {
        currentPlayers.setBet(playerName, bet);
    }

    public void drawCard(String playerName, Card card) {
        currentPlayers.drawCard(playerName, card);
    }

    public ParticipantCurrentHand getPlayersCurrentHand(String playerName) {
        return currentPlayers.getPlayersCurrentHand(playerName);
    }

    public List<ParticipantCurrentHand> getPlayersHand() {
        return currentPlayers.getPlayersHand();
    }

    public boolean isPlayerBust(String playerName) {
        return currentPlayers.isPlayerBust(playerName);
    }

    public List<PlayerStatus> getPlayerStatuses() {
        return currentPlayers.getPlayerStatuses();
    }
}
