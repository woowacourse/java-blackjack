package view;

import exception.ExceptionMessage;
import java.util.List;
import java.util.Scanner;
import util.Parser;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEW_LINE = System.lineSeparator();

    private InputView() {
    }

    public static String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readLine();
    }

    public static String readBattingAmount(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
        return readLine();
    }

    public static String readHitOrStand(String name) {
        System.out.println(NEW_LINE + name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return readLine();
    }

    private static String readLine() {
        String input = scanner.nextLine().strip();
        validateInput(input);
        return input;
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }
}
