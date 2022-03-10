package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_NAME_MESSAGE);
        return splitAndTrim(SCANNER.nextLine());
    }

    private static List<String> splitAndTrim(String line) {
        return Arrays.stream(line.split(DELIMITER))
                .map(token -> token.trim())
                .collect(Collectors.toList());
    }
}
