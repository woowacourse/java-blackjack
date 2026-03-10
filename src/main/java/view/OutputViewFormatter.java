package view;

import dto.DealerFinalResultDto;
import dto.FinalResultDto;
import dto.PlayerDto;
import dto.ResultDto;
import java.util.List;

public class OutputViewFormatter {
    public String formatDealerResult(DealerFinalResultDto dealerFinalResultDto) {
        if (dealerFinalResultDto.drawCount() > 0) {
            return String.format("딜러: %d승 %d무 %d패%n", dealerFinalResultDto.winCount(), dealerFinalResultDto.drawCount(),
                    dealerFinalResultDto.loseCount());
        }
        return String.format("딜러: %d승 %d패%n", dealerFinalResultDto.winCount(), dealerFinalResultDto.loseCount());
    }

    public String formatDealerCardStatus(ResultDto resultDto) {
        return String.format("딜러카드: %s%n", resultDto.cards().getFirst());
    }

    public String formatPlayerCardStatus(PlayerDto playerDto) {
        return String.format("%s카드: %s%n", playerDto.name(), getCardStatusFormat(playerDto.resultDto().cards()));
    }

    public String getCardStatusFormat(List<String> cards) {
        return String.join(", ", cards);
    }

    public String formatCardResult(String name, ResultDto resultDto) {
        return String.format("%n%s카드: %s - 결과: %d", name, getCardStatusFormat(resultDto.cards()), resultDto.score());
    }

    public String formatTotalResult(FinalResultDto finalResultDto) {
        return String.format("%s: %s%n", finalResultDto.name(), finalResultDto.result());
    }
}
