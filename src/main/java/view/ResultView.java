package view;

import domain.player.WinStatus;
import dto.ParticipantResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultView {
    // 딜러와 플레이어들의 카드 보유 내역
    public static void printStartPlayersCards(ParticipantResult dealerResult, List<ParticipantResult> playerResults) {
        List<String> playerNames = new ArrayList<>();
        for (ParticipantResult playerResult : playerResults) {
            playerNames.add(playerResult.name());
        }
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
        System.out.println("딜러카드: " + dealerResult.cardList().getFirst());

        for (ParticipantResult playerResult : playerResults) {
            System.out.println(playerResult.name() + "카드: " + String.join(", ", playerResult.cardList()));
        }
        System.out.println();
    }

    // 플레이어 한 명의 결과 출력
    public static void printPlayerCards(String name, List<String> playCardList) {
        System.out.println(name + "카드: " + String.join(", ", playCardList));
    }

    public static void printDealerOneMoreCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    // 카드 합산 결과
    public static void printCardSumResult(List<ParticipantResult> playerCardList, Map<String, Integer> playerTotalScore) {
        for (ParticipantResult playerResult : playerCardList) {
            List<String> cards = playerResult.cardList();
            Integer score = playerTotalScore.get(playerResult.name());
            System.out.println(playerResult.name() + "카드: " + String.join(", ", cards) + " - 결과: " + score);
        }
        System.out.println();
    }

    public static void printCardSumResult(ParticipantResult dealerResult) {
        System.out.println();
        List<String> dealerCards = dealerResult.cardList();
        System.out.println(dealerResult.name() + "카드: " + String.join(", ", dealerCards) + " - 결과: " + dealerResult.score());
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
