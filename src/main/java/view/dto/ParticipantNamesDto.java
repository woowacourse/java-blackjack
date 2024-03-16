package view.dto;

import java.util.List;
import model.player.Name;

public class ParticipantNamesDto {

    private final List<String> names;
    public ParticipantNamesDto(List<Name> names) {
        this.names = names.stream()
                .map(Name::getValue)
                .toList();
    }

    public List<String> getNames() {
        return names;
    }
}
