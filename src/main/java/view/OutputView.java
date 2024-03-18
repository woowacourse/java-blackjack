package view;

import static domain.blackjack.DealerCardDrawCondition.RAW_DEALER_DRAW_THRESHOLD_POINT;

import java.util.List;

public class OutputView {
    public static void printInitGameDoneMessage(List<String> playerRawNames) {
        String namesOutput = String.join(", ", playerRawNames);
        print("딜러와 %s에게 2장을 나누었습니다.".formatted(namesOutput));
    }

    private static void print(String output) {
        System.out.println(output);
    }

    public static void printDealerDrawDone() {
        print("딜러는 %d이하라 한장의 카드를 더 받았습니다.\n".formatted(RAW_DEALER_DRAW_THRESHOLD_POINT));
    }
}
