package dto;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public record GameScoreDTO(List<HandScoreDTO> players, HandScoreDTO dealer) {

    public static GameScoreDTO from(List<Player> players, Dealer dealer) {
        List<HandScoreDTO> playerHandDTOs = new ArrayList<>();
        for (Player player : players) {
            playerHandDTOs.add(HandScoreDTO.from(player));
        }
        HandScoreDTO dealerHandDTO = HandScoreDTO.from(dealer);
        return new GameScoreDTO(playerHandDTOs, dealerHandDTO);
    }
}
