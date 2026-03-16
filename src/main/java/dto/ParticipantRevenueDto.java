package dto;

import java.math.BigDecimal;

public record ParticipantRevenueDto(
        String name,
        BigDecimal revenue
) {
}
