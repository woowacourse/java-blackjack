package dto;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

public record InitStatusDto(List<String> names, HandDto dealerHandDto, List<PlayerHandDto> playerHandDtos) {

    public static InitStatusDto of(Dealer dealer, List<Player> players) {
        List<String> names = players.stream()
                .map(Player::getNameString)
                .toList();

        HandDto dealerHandDto = HandDto.from(dealer.getFirstCard());

        List<PlayerHandDto> playerHandDtos = players.stream()
                .map(PlayerHandDto::from)
                .toList();

        return new InitStatusDto(names, dealerHandDto, playerHandDtos);
    }
}
