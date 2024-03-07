package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    // TODO: 상수명 변경
    private static final Scanner scanner = new Scanner(System.in);
    private static final String YES = "y";
    private static final String NO = "n";

    public static List<String> readPlayerNames() {
        printPlayerNamesInputMessage();
        String rawInput = scanner.nextLine();
        return List.of(rawInput.split(",", -1));
    }

    // TODO: 메서드명 변경
    public static boolean askForAnotherCard(final String name) {
        printAskingForAnotherCardMessage(name);
        final String rawInput = scanner.nextLine();
        validateYesOrNo(rawInput);
        return YES.equals(rawInput);
    }

    // TODO: 메서드명 변경
    private static void validateYesOrNo(final String input) {
        if (YES.equals(input) || NO.equals(input)) {
            return;
        }

        final String errorMessage = String.format("%s 또는 %s 만 입력할 수 있습니다.", YES, NO);
        throw new IllegalArgumentException(errorMessage);
    }

    private static void printPlayerNamesInputMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    private static void printAskingForAnotherCardMessage(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }
}
