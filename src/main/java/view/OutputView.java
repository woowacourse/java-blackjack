package view;

import dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitInfosOfPlayersAndDealer(final PlayerInfos initPlayerInfos, final DealerInfo initDealerInfo) {
        System.out.println();
        System.out.println("딜러와 pobi, jason에게 2장을 나누었습니다.");
        printInitDealerInfo(initDealerInfo);

        for (final PlayerInfo playerInfo : initPlayerInfos.playerInfos()) {
            printPlayerInfo(playerInfo);
        }
        System.out.println();
    }

    private static void printInitDealerInfo(final DealerInfo initDealerInfo) {
        System.out.println(initDealerInfo.name() + "카드:" + cardInfosToMessage(initDealerInfo.cardInfos().subList(0, 1)));
    }

    public static void printInfosOfPlayersAndDealer(final PlayerInfos playerInfos, final DealerInfo dealerInfo) {
        System.out.println();
        System.out.println(dealerInfo.name() + "카드:" + cardInfosToMessage(dealerInfo.cardInfos()));

        for (final PlayerInfo playerInfo : playerInfos.playerInfos()) {
            printPlayerInfo(playerInfo);
        }
    }

    public static void printPlayerInfo(final PlayerInfo playerInfo) {
        System.out.println(playerInfo.name() + "카드:" + cardInfosToMessage(playerInfo.cardInfos()));
    }

    private static String cardInfosToMessage(final List<CardInfo> cardInfos) {
        return cardInfos.stream()
                .map(cardInfo -> denominationMessage(cardInfo.denomination()) + suitMessage(cardInfo.suit()))
                .collect(Collectors.joining(", "));
    }

    public static void printBlackjackGameResults(final GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 승패");

        System.out.println(gameResult.dealerResult().name() + ": " + gameResult.dealerResult().winCount() + "승" + gameResult.dealerResult().loseCount() + "패");

        for (final PlayerResult playerResult : gameResult.playerResults()) {
            System.out.println(playerResult.name() + ": " + (playerResult.winLose() == WinLose.WIN ? "승" : "패"));
        }
    }

    public static void printDealerHitMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private static String denominationMessage(final String denomination) {
        if (denomination.equals("ACE")) {
            return "A";
        }
        if (denomination.equals("JACK")) {
            return "J";
        }
        if (denomination.equals("QUEEN")) {
            return "Q";
        }
        if (denomination.equals("KING")) {
            return "K";
        }
        if (denomination.equals("TWO")) {
            return "2";
        }
        if (denomination.equals("THREE")) {
            return "3";
        }
        if (denomination.equals("FOUR")) {
            return "4";
        }
        if (denomination.equals("FIVE")) {
            return "5";
        }
        if (denomination.equals("SIX")) {
            return "6";
        }
        if (denomination.equals("SEVEN")) {
            return "7";
        }
        if (denomination.equals("EIGHT")) {
            return "8";
        }
        if (denomination.equals("NINE")) {
            return "9";
        }
        return "10";
    }

    private static String suitMessage(final String suit) {
        if (suit.equals("HEART")) {
            return "하트";
        }
        if (suit.equals("CLUBS")) {
            return "클로버";
        }
        if (suit.equals("SPADE")) {
            return "스페이드";
        }
        return "다이아몬드";
    }
}
