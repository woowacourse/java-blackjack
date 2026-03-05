package view;

import dto.BlackjackResult;
import dto.DealerCardInfo;
import dto.GamblerCardInfo;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {}

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printStartMessage(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }
    public static void printInitMessage(List<String> names) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerFirstCard(String card) {
        System.out.println("딜러카드: " + card);
    }

    public static void printPlayerCards(GamblerCardInfo gamblerCardInfo) {
        System.out.println(gamblerCardInfo.name()+ " " + gamblerCardInfo.card().stream().collect(Collectors.joining(", ")));

    }

    public static void askHit(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printHitResult(String name, String cards) {
        System.out.println(name + "카드: " + cards);
    }

    public static void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalDealer(DealerCardInfo dealerCardInfo, int score) {
        System.out.println("딜러카드: " + String.join("", dealerCardInfo.cards()) + " - 결과: " + score);
    }

    public static void printFinalPlayer(GamblerCardInfo gamblerCardInfo) {
        System.out.println(gamblerCardInfo.name() + "카드: " +
                String.join("", gamblerCardInfo.card()) + " - 결과: " + gamblerCardInfo.score());
    }

    public static void printFinalResultHeader() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public static void printResult(BlackjackResult result) {
        System.out.println("딜러: " + result.winCount() + "승 " + result.lossCount() + "패 " + result.drawCount() + "무");
    }
}