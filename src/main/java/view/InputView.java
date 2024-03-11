package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.dto.GameCompletionResult;

public class InputView {
    private static final String INPUT_PARSE_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String input(String inputRequestMessage) {
        System.out.println(inputRequestMessage);
        return scanner.nextLine();
    }

    public static List<String> inputNames() {
        String initialInput = input("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(initialInput.split(INPUT_PARSE_DELIMITER))
                .map(String::trim)
                .toList();
    }

    public static String inputChoiceCommand(GameCompletionResult gameCompletionResult) {
        return input(gameCompletionResult.getPartipantNameAsString() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }
}
