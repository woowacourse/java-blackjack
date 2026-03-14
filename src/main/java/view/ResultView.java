package view;

import domain.participant.WinStatus;
import domain.vo.Money;
import domain.vo.Name;
import dto.DealerInfoDto;
import dto.PlayerInfoDto;

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
            printPlayerCards(playerInfoDto.getName(), playerInfoDto.getCards());
        }

        System.out.println();
    }

    public static void printPlayerCards(String name, List<String> playCardList) {
        System.out.println(name + "카드: " + String.join(", ", playCardList));
    }

    public static void printDealerOneMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardsAndScoreResult(DealerInfoDto dealerInfoDto, List<PlayerInfoDto> playerInfoDtos) {
        System.out.println();
        System.out.println(dealerInfoDto.getName() + "카드: " + String.join(", ", dealerInfoDto.getCards()) + " - 결과: " + dealerInfoDto.getScore());
        for (PlayerInfoDto playerInfoDto : playerInfoDtos) {
            System.out.println(playerInfoDto.getName() + "카드: " + String.join(", ", playerInfoDto.getCards()) + " - 결과: " + playerInfoDto.getScore());
        }
        System.out.println();
    }

    public static void printRankResult(DealerInfoDto dealerInfoDto, List<PlayerInfoDto> playerInfoDtos) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerInfoDto);
        printPlayerResult(playerInfoDtos);

    }

    public static void printDealerProfit(Money profit) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + profit.getValueOf());
    }

    public static void printPlayerProfit(Name name, Money profit) {
            System.out.println(name.getValueOf() + ": " + profit.getValueOf());
    }

    private static void printDealerResult(DealerInfoDto dealerInfoDto) {
        System.out.println(dealerInfoDto.getName() + ": "
                + dealerInfoDto.getRecord().get(WinStatus.WIN) + "승 "
                + dealerInfoDto.getRecord().get(WinStatus.LOSS) + "패 "
                + dealerInfoDto.getRecord().get(WinStatus.DRAW) + "무");
    }

    private static void printPlayerResult(List<PlayerInfoDto> playerInfoDtos) {
        for (PlayerInfoDto playerInfoDto : playerInfoDtos) {
            System.out.println(playerInfoDto.getName() + ": " + playerInfoDto.getWinStatus().getStatus());
        }
    }
}
