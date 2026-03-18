package dto;

import java.math.BigDecimal;

public record ParticipantResultInfo(
        String name,
        BigDecimal profit
) {
}
