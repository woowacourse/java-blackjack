package domain.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNamesValue = scanner.nextLine();
        List<String> playerNames = Arrays.asList(playerNamesValue.split(DELIMITER));

        for(String playerName : playerNames) {
            if(isNotNumber(playerName)) {
                throw new IllegalArgumentException();
            }
        }

        return playerNames;
    }

    public static String inputGetMoreCard(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",name);
        String input = scanner.nextLine();

        if(isYesOrNo(input)) {
            throw new IllegalArgumentException();
        }

        return input;
    }

    private static boolean isYesOrNo(String input) {
        return YES.equals(input) || NO.equals(input);
    }

    private static boolean isNotNumber(final String playerNamesValue) {
        return playerNamesValue.chars()
                .anyMatch(c -> !Character.isAlphabetic(c));
    }
}
