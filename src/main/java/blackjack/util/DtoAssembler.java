package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.PlayerDto;
import java.util.List;
import java.util.stream.Collectors;

public class DtoAssembler {

    public static PlayerDto createPlayerDto(Player player) {
        List<CardDto> cards = player.getDeckAsList()
            .stream()
            .map(DtoAssembler::creatCardDto)
            .collect(Collectors.toList());

        return new PlayerDto(cards, player.getName(), player.getScore());
    }

    public static CardDto creatCardDto(Card card) {
        return new CardDto(card.getName());
    }

    public static List<PlayerDto> createPlayerDtos(List<Player> players) {
        return players.stream()
            .map(DtoAssembler::createPlayerDto)
            .collect(Collectors.toList());
    }
}
