package dto;

import domain.participant.Dealer;
import domain.participant.Players;
import java.util.List;

public record InitStatusDto(HandDto dealerHandDto, List<PlayerHandDto> playerHandDtos) {

    public static InitStatusDto of(Dealer dealer, Players players) {
        HandDto dealerHandDto = HandDto.from(dealer.getFirstCard());

        List<PlayerHandDto> playerHandDtos = players.players().stream()
                .map(PlayerHandDto::from)
                .toList();

        return new InitStatusDto(dealerHandDto, playerHandDtos);
    }

    public List<String> getNames() {
        return playerHandDtos.stream()
                .map(PlayerHandDto::name)
                .toList();
    }
}
