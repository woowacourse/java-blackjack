package view;

import domain.participant.WinStatus;
import dto.DealerInfoDto;
import dto.DealerResultDto;
import dto.PlayerInfoDto;
import dto.PlayerResultDto;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    public static void printStartPlayersCards(DealerInfoDto dealerInfoDto, List<PlayerInfoDto> playerInfoDtos) {
        String playerNames = playerInfoDtos.stream()
                .map(PlayerInfoDto::getName)
                .collect(Collectors.joining(", "));

        System.out.println("\n" + dealerInfoDto.getName() + "와 " + playerNames + "에게 2장을 나누었습니다.");
        System.out.println(dealerInfoDto.getName() + "카드: " + dealerInfoDto.getCards().get(0));

        for (PlayerInfoDto playerInfoDto : playerInfoDtos) {
            printPlayerCards(playerInfoDto);
        }

        System.out.println();
    }

    public static void printPlayerCards(PlayerInfoDto playerInfoDto) {
        System.out.println(playerInfoDto.getName() + "카드: " + String.join(", ", playerInfoDto.getCards()));
    }

    public static void printDealerOneMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardsAndScoreResult(DealerResultDto dealerInfoDto, List<PlayerResultDto> playerResultDtos) {
        System.out.println();
        System.out.println(dealerInfoDto.getName() + "카드: " + String.join(", ", dealerInfoDto.getCards()) + " - 결과: " + dealerInfoDto.getScore());
        for (PlayerResultDto playerResultDto : playerResultDtos) {
            System.out.println(playerResultDto.getName() + "카드: " + String.join(", ", playerResultDto.getCards()) + " - 결과: " + playerResultDto.getScore());
        }
        System.out.println();
    }

    public static void printRankResult(DealerResultDto dealerResultDto, List<PlayerResultDto> PlayerResultDto) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerResultDto);
        printPlayerResult(PlayerResultDto);

    }

    public static void printDealerProfit(DealerResultDto dto) {
        System.out.println("\n## 최종 수익");
        System.out.println("딜러: " + dto.getProfit().toLong());
    }

    public static void printPlayerProfit(PlayerResultDto playerResultDto) {
            System.out.println(playerResultDto.getName() + ": " + playerResultDto.getProfit().toLong());
    }

    private static void printDealerResult(DealerResultDto dealerResultDto) {
        System.out.println(dealerResultDto.getName() + ": "
                + dealerResultDto.getRecord().get(WinStatus.WIN) + "승 "
                + dealerResultDto.getRecord().get(WinStatus.LOSS) + "패 "
                + dealerResultDto.getRecord().get(WinStatus.DRAW) + "무");
    }

    private static void printPlayerResult(List<PlayerResultDto> playerResultDtos) {
        for (PlayerResultDto playerResultDto : playerResultDtos) {
            System.out.println(playerResultDto.getName() + ": " + playerResultDto.getWinStatus().getStatus());
        }
    }
}
