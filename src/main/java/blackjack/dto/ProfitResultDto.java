package blackjack.dto;

import blackjack.model.participant.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfitResultDto {

    private final String name;
    private final String profit;

    public ProfitResultDto(String name, String profit) {
        this.name = name;
        this.profit = profit;
    }

    public static ProfitResultDto of(Participant participant, int profit) {
        return new ProfitResultDto(participant.getName(), Integer.toString(profit));
    }

    public static List<ProfitResultDto> of(Map<Participant, Integer> profitResults) {
        List<ProfitResultDto> profitResultsDto = new ArrayList<>();
        for (Map.Entry<Participant, Integer> entry : profitResults.entrySet()) {
            profitResultsDto.add(ProfitResultDto.of(entry.getKey(), entry.getValue()));
        }
        return profitResultsDto;
    }

    public String getName() {
        return name;
    }

    public String getProfit() {
        return profit;
    }
}
