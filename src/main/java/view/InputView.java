package view;

import exception.ErrorException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import exception.ExceptionHandler;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        String response = prompt("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return validateAndParsePlayerNames(response);
    }

    public static boolean readAddPlayerCard(String name) {
        return ExceptionHandler.repeatUntilSuccess(() -> {
            String response = prompt(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name));
            validateYesOrNo(response);
            return response.equals("y");
        });
    }

    private static String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    private static List<String> validateAndParsePlayerNames(String response) {
        List<String> playerNames = Arrays.asList(response.split(",", -1));
        validatePlayerNamesBlank(playerNames);
        validatePlayerNamesSize(playerNames);
        return playerNames;
    }

    private static void validatePlayerNamesBlank(List<String> playerNames) {
        for (String playerName : playerNames) {
            validatePlayerNameBlank(playerName);
        }
    }

    private static void validatePlayerNameBlank(String playerName) {
        if (playerName.isBlank()) {
            throw new ErrorException("공백이 아닌 플레이어 이름을 입력해야 합니다.");
        }
    }

    private static void validatePlayerNamesSize(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new ErrorException("최소 1명 이상의 플레이어 이름을 입력해야 합니다.");
        }
    }

    private static void validateYesOrNo(String response) {
        if (!response.equals("y") && !response.equals("n")) {
            throw new ErrorException("y 또는 n만 입력해주세요.");
        }
    }
}
