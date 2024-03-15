package mapper;

import domain.name.Name;
import domain.name.Names;
import domain.participant.Player;
import view.dto.participant.NameDto;

import java.util.List;

public final class NameMapper {
    public static Names namesDtoToNames(final List<NameDto> namesDto) {
        return new Names(namesDto.stream()
                                 .map(nameDto -> new Name(nameDto.name()))
                                 .toList());
    }

    public static NameDto playerToNameDto(final Player player) {
        return new NameDto(player.name()
                                 .value());
    }
}
