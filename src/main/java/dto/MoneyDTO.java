package dto;

import java.util.List;

public record MoneyDTO(
        String dealerMoney,
        List<PlayerOutcomeDto> playerOutcomes
) {
}
