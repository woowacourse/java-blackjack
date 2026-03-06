package view;

import dto.BlackjackResult;
import dto.CardInfo;
import dto.MatchResultLog;
import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printInitMessage(List<String> names) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerFirstCard(String card) {
        System.out.println("딜러카드: " + card);
    }

    public static void printPlayerCards(CardInfo cardInfo) {
        System.out.println(cardInfo.name() + " " + String.join(", ", cardInfo.card()));
    }

    public static void printPlayerBust(String name) {
        System.out.println(name + " 버스트!");
    }

    public static void askHit(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalPlayer(CardInfo cardInfo) {
        System.out.println(cardInfo.name() + "카드: " +
                String.join(", ", cardInfo.card()) + " - 결과: " + cardInfo.score());
    }

    public static void printFinalResultHeader() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public static void printResult(BlackjackResult result) {
        System.out.println("딜러: " + result.winCount() + "승 " + result.lossCount() + "패 " + result.drawCount() + "무");
        for (MatchResultLog gamblerResult : result.matchResultLog()) {
            System.out.println(gamblerResult.name() + ": " + gamblerResult.matchResult().getName());
        }
    }
}