package view;

import dto.DealerFinalResultDto;
import dto.FinalResultDto;
import dto.PlayerDto;
import dto.PlayersDto;
import dto.ProfitDto;
import dto.ResultDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputViewFormatter {
    public String formatHandOutMessage(PlayersDto playersDto) {
        String playersName = playersDto.playersDto().stream()
                .map(PlayerDto::name)
                .collect(Collectors.joining(", "));

        return String.format("%n딜러와 %s에게 2장을 나누었습니다.", playersName);
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

    public String formatProfit(ProfitDto profitDto) {
        return String.format("%s: %d%n", profitDto.name(), profitDto.profit());
    }

    public String formatErrorMessage(String message) {
        return String.format("[ERROR]: %s%n", message);
    }
}
