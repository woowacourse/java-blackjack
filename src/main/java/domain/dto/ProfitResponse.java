package domain.dto;

import java.math.BigDecimal;

public record ProfitResponse(
        String name,
        BigDecimal profit
) {
}
