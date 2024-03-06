package blackjack.domain.dto;

import java.util.List;

public class PlayersDto {

    private final List<PlayerDto> values;

    public PlayersDto(final List<PlayerDto> values) {
        this.values = values;
    }

    public List<PlayerDto> getValues() {
        return values;
    }
}
