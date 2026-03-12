package dto;

import java.util.List;

public record GameResultDto(DealerResultDto dealerResultDto, List<PlayerResultDto> playerResultDto) {

//    public static GameResultDto from(Dealer dealer, Players players,
//                                     Map<Result, Integer> dealerWinLossResults,
//                                     Map<Player, Result> playerWinLossResults) {
//
//        ParticipantDto dealerDto = ParticipantDto.from(dealer);
//
//        List<ParticipantDto> playerDtos = new ArrayList<>();
//        for (Player player : players.getPlayers()) {
//            playerDtos.add(ParticipantDto.from(player));
//        }
//
//        Map<String, Integer> dealerResults = new LinkedHashMap<>();
//        for (Entry<Result, Integer> entry : dealerWinLossResults.entrySet()) {
//            dealerResults.put(entry.getKey().name(), entry.getValue());
//        }
//
//        Map<String, String> playerResults = new LinkedHashMap<>();
//        for (Entry<Player, Result> entry : playerWinLossResults.entrySet()) {
//            playerResults.put(entry.getKey().getName(), entry.getValue().name());
//        }
//
//        return new GameResultDto(dealerDto, playerDtos, dealerResults, playerResults);
//    }
}
