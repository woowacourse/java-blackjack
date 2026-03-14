package dto;

import domain.Players;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public record GameStartDto(List<PlayerHandDto> players, DealerInitialHandDto dealer, List<String> playerNames) {

    public static GameStartDto from(Players players, Dealer dealer) {
        List<PlayerHandDto> playerHandDtos = new ArrayList<>();
        for (Player player : players) {
            playerHandDtos.add(PlayerHandDto.from(player));
        }
        DealerInitialHandDto dealerHandDto = DealerInitialHandDto.from(dealer);
        return new GameStartDto(playerHandDtos, dealerHandDto, getPlayerNames(players));
    }

    private static List<String> getPlayerNames(Players players) {
        return players.getPlayerNames();
    }
}
