package blackjack.view;

import static blackjack.view.expressions.HitStandExpressions.HIT;
import static blackjack.view.expressions.HitStandExpressions.STAND;

import blackjack.view.expressions.HitStandExpressions;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> askPlayerNames() {
        printPlayerNamesInputMessage();
        final String rawInput = scanner.nextLine();

        validateNotBlank(rawInput);

        return List.of(rawInput.split(",", -1));
    }

    public static boolean askForMoreCard(final String name) {
        printAskingForAnotherCardMessage(name);
        final String rawInput = scanner.nextLine();
        return HitStandExpressions.isDrawable(rawInput);   // TODO: 네이밍 변경
    }

    public static int askBettingAmount(final String name) {
        printBettingAmountMessage(name);
        final String rawInput = scanner.nextLine();

        validateNotBlank(rawInput);
        validateNumeric(rawInput);

        return Integer.parseInt(rawInput);
    }

    private static void printPlayerNamesInputMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    private static void printAskingForAnotherCardMessage(final String name) {
        printLineSeparator();
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 " + HIT.getMessage() +
                ", 아니오는 " + STAND.getMessage() + ")");
    }

    private static void printBettingAmountMessage(String name) {
        printLineSeparator();
        System.out.println(String.format("%s의 배팅 금액은?", name));
    }

    private static void validateNotBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다.");
        }
    }

    private static void validateNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수가 아닌 값은 입력할 수 없습니다.");
        }
    }

    private static void printLineSeparator() {
        System.out.println();
    }
}
