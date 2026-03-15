package dto;

import domain.participant.Players;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public record GameScoreDto(List<PlayerHandScoreDto> players, DealerHandScoreDto dealer) {

    public static GameScoreDto from(Players players, Dealer dealer) {
        List<PlayerHandScoreDto> playerHandDtos = new ArrayList<>();
        for (Player player : players) {
            playerHandDtos.add(PlayerHandScoreDto.from(player));
        }
        DealerHandScoreDto dealerHandDto = DealerHandScoreDto.from(dealer);
        return new GameScoreDto(playerHandDtos, dealerHandDto);
    }
}

