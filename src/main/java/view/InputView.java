package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = SCANNER.nextLine();
        return Arrays.stream(names.split(DELIMITER))
                .toList();
    }

    public boolean readCommand(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 " + YES + ", 아니오는 " + NO + ")\n", name);
        String command = SCANNER.nextLine();
        validateCommand(command);
        return YES.equals(command);
    }

    private void validateCommand(String command) {
        if (!YES.equals(command) && !NO.equals(command)) {
            throw new IllegalArgumentException(YES + " 또는 " + NO + "만 입력 가능합니다.");
        }
    }
}
