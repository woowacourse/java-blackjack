package view;

import dto.DealerFinalResultDto;
import dto.FinalResultDto;
import dto.PlayerDto;
import dto.ProfitDto;
import dto.PlayersDto;
import dto.ResultDto;
import dto.TotalFinalResultsDto;
import java.util.stream.Collectors;

public class OutputView {
    private final OutputViewFormatter outputViewFormatter;

    public OutputView(OutputViewFormatter outputViewFormatter) {
        this.outputViewFormatter = outputViewFormatter;
    }

    public void printHandOutMessage(PlayersDto playersDto) {
        System.out.print(outputViewFormatter.formatHandOutMessage(playersDto));
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

    public void printProfitMessage() {
        System.out.println("\n\n## 최종 수익");
    }

    public void printProfit(ProfitDto profitDto) {
        System.out.print(outputViewFormatter.formatProfit(profitDto));
    }
}
