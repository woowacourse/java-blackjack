package dto;

import domain.betting.Revenue;
import domain.participant.Name;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record ParticipantRevenueDto(
        String name,
        BigDecimal revenue) {
    public static List<ParticipantRevenueDto> from(Map<Name, Revenue> profits) {
        List<ParticipantRevenueDto> revenueDtos = new ArrayList<>();
        for (Map.Entry<Name, Revenue> entry : profits.entrySet()) {
            revenueDtos.add(new ParticipantRevenueDto(
                    entry.getKey().getName(),
                    entry.getValue().getMoney()
            ));
        }
        return revenueDtos;
    }
}
