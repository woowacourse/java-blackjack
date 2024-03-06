package view.dto;

import java.util.List;

public record PlayersDto(List<PlayerDto> dtos) {
    public PlayersDto(final List<PlayerDto> dtos) {
        this.dtos = List.copyOf(dtos);
    }
}
