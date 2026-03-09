package dto;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public record GameStartDTO(List<HandDTO> players, DealerInitialHandDTO dealer, List<String> playerNames) {

    public static GameStartDTO from(List<Player> players, Dealer dealer) {
        List<HandDTO> playerHandDTOs = new ArrayList<>();
        for (Player player : players) {
            playerHandDTOs.add(HandDTO.from(player));
        }
        DealerInitialHandDTO dealerHandDTO = DealerInitialHandDTO.from(dealer);
        return new GameStartDTO(playerHandDTOs, dealerHandDTO, getPlayerNames(players));
    }

    private static List<String> getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
