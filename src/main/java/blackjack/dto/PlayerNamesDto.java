package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerNamesDto {

    private final List<String> names;

    private PlayerNamesDto(List<String> names) {
        this.names = names;
    }

    public static PlayerNamesDto from(List<Player> players) {
        List<String> names = players.stream()
                                    .map(Participant::getName)
                                    .collect(Collectors.toList());
        return new PlayerNamesDto(names);
    }

    public List<String> getNames() {
        return names;
    }
}
