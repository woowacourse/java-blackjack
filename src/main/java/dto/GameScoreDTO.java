package dto;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public class GameScoreDTO {
    private final List<ParticipantHandDTO> players;
    private final ParticipantHandDTO dealer;

    private GameScoreDTO(List<ParticipantHandDTO> players, ParticipantHandDTO dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<ParticipantHandDTO> getPlayers() {
        return players;
    }

    public ParticipantHandDTO getDealer() {
        return dealer;
    }

    public static GameScoreDTO from(List<Player> players, Dealer dealer) {
        List<ParticipantHandDTO> playerHandDTOs = new ArrayList<>();
        for (Player player : players) {
            playerHandDTOs.add(new ParticipantHandDTO(player, player.getHandCards()));
        }
        ParticipantHandDTO dealerHandDTO = new ParticipantHandDTO(dealer, dealer.getHandCards());
        return new GameScoreDTO(playerHandDTOs, dealerHandDTO);
    }
}
