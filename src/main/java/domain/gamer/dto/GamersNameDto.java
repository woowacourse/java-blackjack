package domain.gamer.dto;

import domain.gamer.Gamer;

import java.util.List;

public record GamersNameDto(
        List<String> playerNames
) {

    public static GamersNameDto from(List<? extends Gamer> gamers) {
        List<String> playerNames = gamers.stream()
                .map(Gamer::getName)
                .toList();
        return new GamersNameDto(playerNames);
    }

}
