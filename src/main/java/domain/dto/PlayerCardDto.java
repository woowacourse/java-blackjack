package domain.dto;

import domain.Dealer;
import domain.Player;

import java.util.List;

public record PlayerCardDto(String name, List<CardDto> cards) {
    public static PlayerCardDto from(Player player) {
        List<CardDto> cardDtos = player.getCards().stream()
                .map(CardDto::from)
                .toList();

        return new PlayerCardDto(player.getName(), cardDtos);
    }

    public static PlayerCardDto fromDealer(Dealer dealer) {
        CardDto firstCard = CardDto.from(dealer.getFirstCard());
        return new PlayerCardDto(dealer.getName(), List.of(firstCard));
    }
}
