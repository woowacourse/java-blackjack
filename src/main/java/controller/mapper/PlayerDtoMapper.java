package controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import controller.dto.PlayerDto;
import domain.hand.Hand;
import domain.participant.Player;

public class PlayerDtoMapper {

    public static List<PlayerDto> map(List<? extends Player> players) {
        return players
                .stream()
                .map(PlayerDtoMapper::map)
                .collect(Collectors.toList());
    }

    private static PlayerDto map(Player player) {
        Hand hand = player.getHand();
        return new PlayerDto(player.getName(),
                CardDtoMapper.map(hand.getCards()),
                hand.score().getScore());
    }
}
