package ui.dto;

import domain.participant.Player;
import java.util.List;

public record PlayerDto(
        String name,
        List<CardDto> cards,
        int score
) {
    public static PlayerDto toDto(Player player) {
        return new PlayerDto(player.getName().getValue(), CardDto.toDtoList(player.getState().hand().getCards()),
                player.getState().hand().totalSum().value());
    }

    public static List<PlayerDto> toDtoList(List<Player> players) {
        return players.stream()
                .map(PlayerDto::toDto)
                .toList();
    }
}
