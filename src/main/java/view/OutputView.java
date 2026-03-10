package view;

import dto.DealerFinalResultDto;
import dto.PlayerDto;
import dto.PlayersDto;
import dto.ResultDto;
import dto.TotalFinalResultsDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private final OutputViewFormatter outputViewFormatter;

    public OutputView(OutputViewFormatter outputViewFormatter) {
        this.outputViewFormatter = outputViewFormatter;
    }

    public void printHandOutMessage(PlayersDto playersDto) {
        String playersName = playersDto.playersDto().stream()
                .map(PlayerDto::name)
                .collect(Collectors.joining(","));

        System.out.print("\n딜러와 " + playersName + "에게 2장을 나누었습니다.");
    }

    public void printDealerCardStatus(ResultDto resultDto) {
        System.out.print(System.lineSeparator());
        System.out.print(outputViewFormatter.formatDealerCardStatus(resultDto));
    }

    public void printPlayerCardStatus(PlayerDto playerDto) {
        System.out.print(outputViewFormatter.formatPlayerCardStatus(playerDto));
    }

    public void printCardStatus(PlayersDto playersDto, ResultDto resultDto) {
        printDealerCardStatus(resultDto);
        for (PlayerDto playerDto : playersDto.playersDto()) {
            printPlayerCardStatus(playerDto);
        }
        System.out.print(System.lineSeparator());
    }

    public void printAddDealerCardMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardResult(ResultDto resultDto, PlayersDto playersDto) {
        printDealerCardResult(resultDto);
        printPlayersCardResult(playersDto);
    }

    private void printDealerCardResult(ResultDto resultDto) {
        System.out.print(outputViewFormatter.formatCardResult("딜러", resultDto));
    }

    private void printPlayersCardResult(PlayersDto playersDto) {
        for (PlayerDto playerDto : playersDto.playersDto()) {
            System.out.print(outputViewFormatter.formatCardResult(playerDto.name(), playerDto.resultDto()));
        }
    }

    public void printTotalResult(DealerFinalResultDto dealerFinalResultDto,
                                        TotalFinalResultsDto totalFinalResultsDto) {
        System.out.println("\n\n## 최종 승패");
        System.out.print(outputViewFormatter.formatDealerResult(dealerFinalResultDto));
        for (String finalResult : totalFinalResultsDto.totalResults()) {
            System.out.print(finalResult);
        }
    }
}
