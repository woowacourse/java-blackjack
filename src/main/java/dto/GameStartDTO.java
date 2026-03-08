package dto;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameStartDTO {
    private final List<ParticipantHandDTO> players;
    private final ParticipantHandDTO dealer;
    private final String playerNames;

    private GameStartDTO(List<ParticipantHandDTO> players, ParticipantHandDTO dealer) {
        this.players = players;
        this.dealer = dealer;
        this.playerNames = getPlayerNames(players);
    }

    private static String getPlayerNames(List<ParticipantHandDTO> players) {
        return players
                .stream()
                .map(ParticipantHandDTO::getName)
                .collect(Collectors.joining(","));
    }

    public List<ParticipantHandDTO> getPlayers() {
        return players;
    }

    public ParticipantHandDTO getDealer() {
        return dealer;
    }

    public String getPlayerNames() {
        return playerNames;
    }

    public static GameStartDTO from(List<Player> players, Dealer dealer) {
        List<ParticipantHandDTO> playerHandDTOs = new ArrayList<>();
        for (Player player : players) {
            playerHandDTOs.add(new ParticipantHandDTO(player, player.getHandCards()));
        }
        ParticipantHandDTO dealerHandDTO = new ParticipantHandDTO(dealer, dealer.getFirstCard());
        return new GameStartDTO(playerHandDTOs, dealerHandDTO);
    }
}
