package Blackjack.view;

import Blackjack.exception.ErrorException;
import Blackjack.exception.ExceptionHandler;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        String response = prompt("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return validateAndParsePlayerNames(response);
    }

    public static boolean readAddPlayerCard(final String name) {
        return ExceptionHandler.repeatUntilSuccess(() -> {
            final String response = prompt(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name));
            validateYesOrNo(response);
            return response.equals("y");
        });
    }

    private static String prompt(final String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    private static List<String> validateAndParsePlayerNames(final String response) {
        final List<String> playerNames = Arrays.asList(response.split(","));
        validatePlayerNamesSize(playerNames);
        return playerNames;
    }

    private static void validatePlayerNamesSize(final List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new ErrorException("최소 1명 이상의 플레이어 이름을 입력해야 합니다.");
        }
    }

    private static void validateYesOrNo(final String response) {
        if (!response.equals("y") && !response.equals("n")) {
            throw new ErrorException("y 또는 n만 입력해주세요.");
        }
    }
}
