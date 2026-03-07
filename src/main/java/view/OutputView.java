package view;

import dto.DealerFinalResultDto;
import dto.DealerResultDto;
import dto.PlayerDto;
import dto.PlayersDto;
import dto.TotalFinalResultsDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printHandOutMessage(PlayersDto playersDto) {
        String playersName = playersDto.playersDto().stream()
                .map(PlayerDto::name)
                .collect(Collectors.joining(","));

        System.out.print("\n딜러와 " + playersName + "에게 2장을 나누었습니다.");
    }

    // 딜러카드 출력
    public static void printDealerCardStatus(DealerResultDto dealerResultDto) {
        System.out.printf("%n딜러카드: %s%n", getDealerCardStatus(dealerResultDto));
    }

    //플레이어 카드 출력
    public static void printPlayerCardStatus(PlayerDto playerDto) {
        System.out.printf("%s카드: %s%n", playerDto.name(), getCardStatusFormat(playerDto.playerResultDto().cards()));
    }

    public static void printCardStatus(PlayersDto playersDto, DealerResultDto dealerResultDto) {
        printDealerCardStatus(dealerResultDto);
        for (PlayerDto playerDto : playersDto.playersDto()) {
            printPlayerCardStatus(playerDto);
        }
        System.out.print(System.lineSeparator());
    }

    private static String getDealerCardStatus(DealerResultDto dealerResultDto) {
        return dealerResultDto.cards().getFirst();
    }

    private static String getCardStatusFormat(List<String> cards) {
        return String.join(", ", cards);
    }

    public static void printAddDealerCardMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(DealerResultDto dealerResultDto, PlayersDto playersDto) {
        printDealerCardResult(dealerResultDto);
        printPlayersCardResult(playersDto);
    }

    private static void printDealerCardResult(DealerResultDto dealerResultDto) {
        System.out.printf("%n딜러카드: %s - 결과: %d", getCardStatusFormat(dealerResultDto.cards()),
                dealerResultDto.score());
    }

    private static void printPlayersCardResult(PlayersDto playersDto) {
        for (PlayerDto playerDto : playersDto.playersDto()) {
            printPlayerCardResult(playerDto);
        }
    }

    // TODO 버스트 나면 버스트로 표시?
    private static void printPlayerCardResult(PlayerDto playerDto) {
        System.out.printf("%n%s카드: %s - 결과: %d", playerDto.name(),
                getCardStatusFormat(playerDto.playerResultDto().cards()),
                playerDto.playerResultDto().score());
    }

    public static void printTotalResult(DealerFinalResultDto dealerFinalResultDto,
                                        TotalFinalResultsDto totalFinalResultsDto) {
        System.out.println("\n\n## 최종 승패");
        System.out.print(dealerFinalResultDto.result());
        for (String finalResult : totalFinalResultsDto.totalResults()) {
            System.out.print(finalResult);
        }
    }
}
