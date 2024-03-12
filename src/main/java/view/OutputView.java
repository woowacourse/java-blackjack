package view;

import java.util.List;

public class OutputView {

    public static void printStartGame() {
        print("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    private static void print(String output) {
        System.out.println(output);
    }

    public static void printInitGameDoneMessage(List<String> playerRawNames) {
        String namesOutput = String.join(", ", playerRawNames);
        print("딜러와 %s에게 2장을 나누었습니다.".formatted(namesOutput));
    }

    public static void printDealerDrawDone() {
        print("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }
}
