package view;

import domain.player.WinStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultView {
    // 딜러와 플레이어들의 카드 보유 내역
    public static void printStartPlayersCards(Map<String, List<String>> playerCardList) {
        List<String> playerNames = new ArrayList<>();
        for (String playerName : playerCardList.keySet()) {
            if (playerName.equals("딜러")) {
                continue;
            }
            playerNames.add(playerName);
        }

        System.out.println("\n딜러와 " + String.join(", ", playerNames) +"에게 2장을 나누었습니다.");

        for (String playerName : playerCardList.keySet()) {
            List<String> cards = playerCardList.get(playerName);

            if (playerName.equals("딜러")) {
                System.out.println(playerName + "카드: " + cards.get(0));

                continue;
            }

            System.out.println(playerName + "카드: " + String.join(", ", cards));
        }
        System.out.println();
    }

    // 플레이어 한 명의 결과 출력
    public static void printPlayerCards(String name, List<String> playCardList) {
        System.out.println(name + "카드: " + String.join(", ", playCardList));
    }

    public static void printDealerOneMoreCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    // 카드 합산 결과
    public static void printCardSumResult(Map<String, List<String>> playerCardList, Map<String, Integer> playerTotalScore) {
        System.out.println();
        for (String playerName : playerCardList.keySet()) {
            List<String> cards = playerCardList.get(playerName);
            Integer score = playerTotalScore.get(playerName);
            System.out.println(playerName + "카드: " + String.join(", ", cards) + " - 결과: " + score);
        }
        System.out.println();
    }

    public static void printResult(Map<String, WinStatus> playerResult) {
        System.out.println("## 최종 승패");
        printDealerResult(playerResult);
        printPlayerResult(playerResult);

    }

    private static void printDealerResult(Map<String, WinStatus> playerResult) {
        int dealerWin = 0;
        int dealerLose = 0;
        int dealerDraw = 0;

        for (WinStatus status : playerResult.values()) {
            if(status == WinStatus.WIN) {
                dealerLose++;
            }

            if (status == WinStatus.LOSE) {
                dealerWin++;
            }

            if (status == WinStatus.DRAW) {
                dealerDraw++;
            }
        }

        System.out.println("딜러: " + dealerWin + "승 " + dealerLose + "패 " + dealerDraw + "무");
    }

    private static void printPlayerResult(Map<String, WinStatus> playerResult) {
        for (String name : playerResult.keySet()) {
            System.out.println(name + ": " + playerResult.get(name).getStatus());
        }
    }
}
