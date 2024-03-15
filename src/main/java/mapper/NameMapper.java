package mapper;

import domain.name.Name;
import domain.name.Names;
import view.dto.participant.NameDto;

import java.util.List;

public final class NameMapper {
    public static Names namesDtoToNames(List<NameDto> namesDto) {
        return new Names(namesDto.stream()
                                 .map(nameDto -> new Name(nameDto.name()))
                                 .toList());
    }
}
