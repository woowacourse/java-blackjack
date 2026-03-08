package dto;

import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.Result;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public record GameResultDto(ParticipantDto dealerDto,
                            List<ParticipantDto> playerDtos,
                            Map<String, Integer> dealerWinLossResults,
                            Map<String, String> playerWinLossResults) {

    public static GameResultDto from(Dealer dealer, Players players,
                                     Map<Result, Integer> dealerWinLossResults,
                                     Map<Player, Result> playerWinLossResults) {
        
        ParticipantDto dealerDto = ParticipantDto.from(dealer);

        List<ParticipantDto> playerDtos = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            playerDtos.add(ParticipantDto.from(player));
        }

        Map<String, Integer> dealerResults = new LinkedHashMap<>();
        for (Entry<Result, Integer> entry : dealerWinLossResults.entrySet()) {
            dealerResults.put(entry.getKey().name(), entry.getValue());
        }

        Map<String, String> playerResults = new LinkedHashMap<>();
        for (Entry<Player, Result> entry : playerWinLossResults.entrySet()) {
            playerResults.put(entry.getKey().getName(), entry.getValue().name());
        }

        return new GameResultDto(dealerDto, playerDtos, dealerResults, playerResults);
    }
}
