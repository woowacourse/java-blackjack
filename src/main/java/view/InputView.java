package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final int WITH_EMPTY_TOKENS = -1;

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_NAME_MESSAGE);
        return splitAndTrim(SCANNER.nextLine());
    }

    private static List<String> splitAndTrim(String line) {
        return Arrays.stream(line.split(DELIMITER, WITH_EMPTY_TOKENS))
                .map(token -> token.trim())
                .collect(Collectors.toList());
    }

    public static boolean inputHitResponse(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String option = SCANNER.nextLine();
        checkYesOrNo(option);
        return convertOptionToBoolean(option);
    }

    private static boolean convertOptionToBoolean(String option) {
        return option.equalsIgnoreCase("y");
    }

    private static void checkYesOrNo(String response) {
        if (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n"))) {
            throw new IllegalArgumentException("Y 또는 N을 입력해주세요.");
        }
    }
}
