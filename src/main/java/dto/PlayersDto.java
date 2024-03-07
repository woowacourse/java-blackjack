package dto;

import domain.Dealer;
import domain.Participant;
import domain.Players;

import java.util.ArrayList;
import java.util.List;

public class PlayersDto {

    private final List<PlayerDto> players;

    private PlayersDto(final List<PlayerDto> players) {
        this.players = players;
    }

    public static PlayersDto from(final Dealer dealer, final Players players) { // TODO 추상화 고려
        PlayersDto playersDto = from(players);
        playersDto.players.add(0, PlayerDto.from(dealer));
        return playersDto;
    }

    public static PlayersDto from(final Players players) {
        List<PlayerDto> result = new ArrayList<>();
        for (Participant participant : players.getPlayers()) {
            result.add(PlayerDto.from(participant));
        }

        return new PlayersDto(result);
    }

    public List<String> getNames() {
        return players.stream()
                .map(PlayerDto::getName)
                .toList();
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }
}
