package view;

import dto.BlackjackResult;
import dto.PlayerCardInfo;
import java.util.List;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printBettingMessage(String name) {
        System.out.println(name + "의 배팅금액은?");
    }

    public static void printInitMessage(List<String> names) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerFirstCard(String card) {
        System.out.println("딜러카드: " + card);
    }

    public static void printGamblerCards(String name, PlayerCardInfo playerCardInfo) {
        System.out.println(name + "카드 : " + String.join(", ", playerCardInfo.card()));
    }

    public static void printDealerCards(PlayerCardInfo playerCardInfo) {
        System.out.println("딜러카드 : " + String.join(", ", playerCardInfo.card()));
    }

    public static void printPlayerBust(String name) {
        System.out.println(name + " 버스트!");
    }

    public static void printPlayerBlackJack(String name) {
        System.out.println(name + " 블랙잭!");
    }

    public static void askHit(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalGambler(String name, PlayerCardInfo playerCardInfo) {
        System.out.println(name + "카드: " +
                String.join(", ", playerCardInfo.card()) + " - 결과: " + playerCardInfo.score());
    }

    public static void printFinalDealer(PlayerCardInfo playerCardInfo) {
        System.out.println("딜러카드: " +
                String.join(", ", playerCardInfo.card()) + " - 결과: " + playerCardInfo.score());
    }

    public static void printFinalResultHeader() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public static void printResult(BlackjackResult result) {
        System.out.println("딜러: " + result.dealerProfit());
        for (Map.Entry<String, Integer> entry : result.matchResultLog().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
