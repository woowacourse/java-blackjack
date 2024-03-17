package view;

import domain.participant.PlayerName;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final List<String> DENIED_NAMES = List.of("딜러", "DEALER");
    private static final String YES_STRING = "y";
    private static final String NO_STRING = "n";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String inputString = scanner.nextLine();
        validateInputString(inputString);

        System.out.println();

        return Arrays.stream(inputString.split(",")).toList();
    }

    private static void validateInputString(final String inputString) {
        final boolean hasDeniedString = DENIED_NAMES.stream()
                .anyMatch(deniedName -> inputString.toUpperCase().contains(deniedName));
        if (hasDeniedString) {
            throw new IllegalArgumentException("시스템 상에서 사용되는 단어는 이름으로 설정할 수 없습니다.");
        }
    }

    public static boolean inputDrawDecision(final PlayerName playerName) {
        System.out.println(playerName.value() + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        final String inputString = scanner.nextLine();

        return convertDecisionToBoolean(inputString);
    }

    private static boolean convertDecisionToBoolean(final String input) {
        if (input.equals(YES_STRING)) {
            return true;
        }

        if (input.equals(NO_STRING)) {
            return false;
        }

        throw new IllegalArgumentException("카드를 받을지 여부는 y/n만 입력할 수 있습니다.");
    }

    public static int inputBettingMoney(final PlayerName playerName) {
        System.out.println(playerName.value() + "의 배팅 금액은?");

        final String input = scanner.nextLine();
        System.out.println();

        return parseNumber(input);
    }

    private static int parseNumber(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수만 입력할 수 있습니다.");
        }
    }
}
