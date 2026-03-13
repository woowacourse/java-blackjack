package dto;

import domain.Players;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public record GameScoreDto(List<HandScoreDto> players, HandScoreDto dealer) {

    public static GameScoreDto from(Players players, Dealer dealer) {
        List<HandScoreDto> playerHandDtos = new ArrayList<>();
        for (Player player : players) {
            playerHandDtos.add(HandScoreDto.from(player));
        }
        HandScoreDto dealerHandDto = HandScoreDto.from(dealer);
        return new GameScoreDto(playerHandDtos, dealerHandDto);
    }
}

