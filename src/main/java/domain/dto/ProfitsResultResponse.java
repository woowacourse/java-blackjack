package domain.dto;

import domain.Profits;
import java.util.List;

public record ProfitsResultResponse(List<ProfitResponse> results) {

    public static ProfitsResultResponse from(Profits profits) {
        List<ProfitResponse> results = profits.getParticipantProfits().entrySet().stream()
                .map(entry -> new ProfitResponse(
                        entry.getKey().getName(),
                        entry.getValue().toInt()
                ))
                .toList();

        return new ProfitsResultResponse(results);
    }
}
