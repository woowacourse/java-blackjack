package team.blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import team.blackjack.control.dto.DrawResult;

public class OutputView {

    private static void println(String message) {
        System.out.println(message);
    }

    private static void print(String message) {
        System.out.print(message);
    }

    public static void printPlayerNameRequest() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printPlayerActionRequest(String playerName) {
        println("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".formatted(playerName));
    }

    public static void printDrawResult(DrawResult result) {
        String playerNames = String.join(", ", result.playerNames());
        println("딜러와 %s에게 2장을 나누었습니다.".formatted(playerNames));

        println("딜러카드: %s".formatted(result.dealerCard()));
        for (Entry<String, List<String>> entry : result.playerCards().entrySet()) {
            String playerCardNames = String.join(", ", entry.getValue());
            println("%s카드: %s".formatted(entry.getKey(),playerCardNames));
        }

    }
}
