
package dto;

import domain.Players;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public record GameStartDto(List<HandDto> players, DealerInitialHandDto dealer, List<String> playerNames) {

    public static GameStartDto from(Players players, Dealer dealer) {
        List<HandDto> playerHandDtos = new ArrayList<>();
        for (Player player : players) {
            playerHandDtos.add(HandDto.from(player));
        }
        DealerInitialHandDto dealerHandDto = DealerInitialHandDto.from(dealer);
        return new GameStartDto(playerHandDtos, dealerHandDto, getPlayerNames(players));
    }

    private static List<String> getPlayerNames(Players players) {
        return players.getPlayerNames();
    }
}
