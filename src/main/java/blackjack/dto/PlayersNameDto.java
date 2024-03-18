package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;

public record PlayersNameDto(List<String> names) {

    public static PlayersNameDto from(List<Player> players) {
        List<String> names = players.stream()
                .map(Participant::getName)
                .toList();

        return new PlayersNameDto(names);
    }
}
