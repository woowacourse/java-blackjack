package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_PARSE_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String input(String inputRequestMessage) {
        System.out.println(inputRequestMessage);
        return scanner.nextLine();
    }

    public static List<String> inputNames(String inputRequestMessage) {
        String initialInput = validateContainsDealerName(scanner.nextLine());
        return Arrays.stream(initialInput.split(INPUT_PARSE_DELIMITER))
                .map(String::trim)
                .toList();
    }

    private static String validateContainsDealerName(String inputName) {
        if (inputName.contains("딜러")) {
            throw new IllegalArgumentException("'딜러'가 포함된 사용자명은 사용할 수 없습니다.");
        }
        return inputName;
    }
}
