package view.gameresult;

import domain.blackjack.GameResult;
import dto.DealerGameResultDTO;
import java.util.Map;
import java.util.stream.Collectors;

class DealerGameResultOutputGenerator {
    static String generate(DealerGameResultDTO dealerGameResultDTO) {
        Map<GameResult, Integer> dealerGameResultCounts = dealerGameResultDTO.getDealerGameResultCounts();
        return dealerGameResultCounts.entrySet()
                .stream()
                .map(mapEntry -> {
                    Integer resultCount = mapEntry.getValue();
                    ViewGameResult viewGameResult = ViewGameResult.of(mapEntry.getKey());
                    String viewGameResultOutput = viewGameResult.getOutput();
                    return "%d%s".formatted(resultCount, viewGameResultOutput);
                })
                .collect(Collectors.joining(" "));
    }
}
