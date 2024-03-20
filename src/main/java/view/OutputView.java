package view;

import dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitInfosOfPlayersAndDealer(final PlayerInfos initPlayerInfos, final DealerInfo initDealerInfo) {
        System.out.println();
        System.out.println("딜러와 " + playerNamesToMessage(initPlayerInfos) + "에게 2장을 나누었습니다.");
        printInitDealerInfo(initDealerInfo);

        for (final PlayerInfo playerInfo : initPlayerInfos.playerInfos()) {
            printPlayerInfo(playerInfo);
        }
        System.out.println();
    }

    private static String playerNamesToMessage(final PlayerInfos initPlayerInfos) {
        return String.join(", ", initPlayerInfos.playerInfos().stream().map(PlayerInfo::name).toList());
    }

    private static void printInitDealerInfo(final DealerInfo initDealerInfo) {
        System.out.println(initDealerInfo.name() + "카드: " + cardInfosToMessage(initDealerInfo.cardInfos().subList(0, 1)));
    }

    public static void printInfosOfPlayersAndDealer(final PlayerInfos playerInfos, final DealerInfo dealerInfo) {
        System.out.println();
        System.out.println(dealerInfo.name() + "카드: " + cardInfosToMessage(dealerInfo.cardInfos()));

        for (final PlayerInfo playerInfo : playerInfos.playerInfos()) {
            printPlayerInfo(playerInfo);
        }
    }

    public static void printPlayerInfo(final PlayerInfo playerInfo) {
        System.out.println(playerInfo.name() + "카드: " + cardInfosToMessage(playerInfo.cardInfos()));
    }

    private static String cardInfosToMessage(final List<CardInfo> cardInfos) {
        return cardInfos.stream()
                .map(cardInfo -> Denomination.messageOf(cardInfo.denomination()) + Suit.messageOf(cardInfo.suit()))
                .collect(Collectors.joining(", "));
    }

    public static void printBlackjackGameResults(final GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 수익");

        System.out.println(gameResult.dealerResult().name() + ": " + gameResult.dealerResult().profit());

        for (final PlayerResult playerProfit : gameResult.playerResults()) {
            System.out.println(playerProfit.name() + ": " + playerProfit.profit());
        }
    }

    public static void printDealerHitMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
