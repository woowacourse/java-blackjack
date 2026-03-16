package blackjack.dto;

import java.util.List;

public record PlayerProfitsDto(List<PlayerProfitDto> results) {

    public static PlayerProfitsDto from(List<PlayerProfitDto> playerProfits) {
        return new PlayerProfitsDto(playerProfits);
    }
}
