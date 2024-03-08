package blackjack.view;

import java.util.List;
import java.util.Scanner;

import static blackjack.view.PlayerChoice.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> askPlayerNames() {
        printPlayerNamesInputMessage();
        String rawInput = scanner.nextLine();
        return List.of(rawInput.split(",", -1));
    }

    public static boolean askForMoreCard(final String name) {
        printAskingForAnotherCardMessage(name);
        final String rawInput = scanner.nextLine();
        return PlayerChoice.isDrawable(rawInput);
    }

    private static void printPlayerNamesInputMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    private static void printAskingForAnotherCardMessage(final String name) {
        printLineSeparator();
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 " + HIT.getMessage() +
                ", 아니오는 " + STAND.getMessage() + ")");
    }

    private static void printLineSeparator() {
        System.out.println();
    }
}
