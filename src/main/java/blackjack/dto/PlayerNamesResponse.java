package blackjack.dto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackjack.domain.participant.Name;
import java.util.List;

public class PlayerNamesResponse {
    private final List<String> names;

    public PlayerNamesResponse(List<String> names) {
        this.names = names;
    }

    public static PlayerNamesResponse of(List<Name> names) {
        return names.stream()
                .map(Name::getName)
                .collect(collectingAndThen(toList(), PlayerNamesResponse::new));
    }

    public List<String> getNames() {
        return names;
    }
}
