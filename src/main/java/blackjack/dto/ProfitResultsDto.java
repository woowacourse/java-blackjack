package blackjack.dto;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProfitResultsDto {
    private final List<ProfitResultDto> profitResultsDto;

    public ProfitResultsDto(List<ProfitResultDto> profitResultsDto) {
        this.profitResultsDto = profitResultsDto;
    }

    public static ProfitResultsDto of(Map<String, Integer> profitResult) {
        List<ProfitResultDto> profitResultsDto = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : profitResult.entrySet()) {
            profitResultsDto.add(new ProfitResultDto(entry.getKey(), entry.getValue()));
        }
        return new ProfitResultsDto(profitResultsDto);
    }

    public List<ProfitResultDto> getProfitResultsDto() {
        return profitResultsDto;
    }
}
