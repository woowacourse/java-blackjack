package view;

import dto.PlayerDto;
import dto.PlayersDto;
import dto.ResultDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printHandOutMessage(PlayersDto playersDto) {
        String playersName = playersDto.playersDto().stream()
                .map(PlayerDto::name)
                .collect(Collectors.joining(","));

        System.out.print("딜러와 " + playersName + "에게 2장을 나누었습니다.");
    }

    public static void printDealerCardStatus(ResultDto resultDto) {
        System.out.printf("%n딜러카드: %s%n", getCardStatusFormat(resultDto.cards()));
    }

    public static void printPlayerCardStatus(PlayerDto playerDto) {
        System.out.printf("%s카드: %s%n", playerDto.name(), getCardStatusFormat(playerDto.resultDto().cards()));
    }

    public static void printCardStatus(PlayersDto playersDto, ResultDto resultDto) {
        printDealerCardStatus(resultDto);
        for (PlayerDto playerDto : playersDto.playersDto()) {
            printPlayerCardStatus(playerDto);
        }
        System.out.print(System.lineSeparator());
    }

    private static String getCardStatusFormat(List<String> cards) {
        return String.join(", ", cards);
    }

    public static void printAddDealerCardMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(ResultDto resultDto, PlayersDto playersDto) {
        printDealerCardResult(resultDto);
        printPlayersCardResult(playersDto);
    }

    private static void printDealerCardResult(ResultDto resultDto) {
        System.out.printf("%n딜러카드: %s - 결과: %d",
                getCardStatusFormat(resultDto.cards()),
                resultDto.score());
    }

    private static void printPlayersCardResult(PlayersDto playersDto) {
        for (PlayerDto playerDto : playersDto.playersDto()) {
            printPlayerCardResult(playerDto);
        }
    }

    private static void printPlayerCardResult(PlayerDto playerDto) {
        System.out.printf("%n%s카드: %s - 결과: %d", playerDto.name(),
                getCardStatusFormat(playerDto.resultDto().cards()),
                playerDto.resultDto().score());
    }

    public static void printTotalProfit(String dealerProfit, List<String> playerProfitResults) {
        System.out.println("\n\n## 최종 수익");
        System.out.printf("딜러: %s%n", dealerProfit);
        for (String playerProfitResult : playerProfitResults) {
            System.out.print(playerProfitResult);
        }
    }
}
