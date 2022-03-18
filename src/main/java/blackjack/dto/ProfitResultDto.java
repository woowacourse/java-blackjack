package blackjack.dto;

import blackjack.domain.Participant;
import blackjack.domain.ProfitResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfitResultDto {

    private final double dealerProfit;
    private final Map<ParticipantDto, Double> playersProfit;

    private ProfitResultDto(double dealerProfit, Map<ParticipantDto, Double> playersProfit) {
        this.dealerProfit = dealerProfit;
        this.playersProfit = new LinkedHashMap<>(playersProfit);
    }

    public static ProfitResultDto from(ProfitResult profitResult) {
        Map<Participant, Double> playersProfit = profitResult.getPlayersProfit();
        Map<ParticipantDto, Double> dtoMap = toDtoMap(playersProfit);
        return new ProfitResultDto(profitResult.getDealerProfit(), dtoMap);
    }

    private static Map<ParticipantDto, Double> toDtoMap(Map<Participant, Double> playersProfit) {
        List<Participant> toList = new ArrayList<>(playersProfit.keySet());

        return toList.stream()
                .collect(Collectors.toMap(ParticipantDto::from, playersProfit::get));
    }

    public double getDealerProfit() {
        return dealerProfit;
    }

    public Map<ParticipantDto, Double> getPlayersProfit() {
        return playersProfit;
    }
}
