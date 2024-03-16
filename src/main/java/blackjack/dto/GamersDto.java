package blackjack.dto;

import java.util.List;

public record GamersDto(
        DealerDto dealerDto,
        List<PlayerDto> playerDtos) {
}
