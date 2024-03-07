package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String YES = "y";
    private static final String NO = "n";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으롭 분리)");
        String inputString = scanner.nextLine();
        System.out.println();
        return Arrays.stream(inputString.split(",")).toList();
    }

    public static boolean inputDrawDecision() {
        String inputString = scanner.nextLine();
        return convertInputToDecision(inputString);
    }

    private static boolean convertInputToDecision(String input) {
        if (input.equals(YES)) {
            return true;
        }

        if (input.equals(NO)) {
            return false;
        }

        throw new IllegalArgumentException("카드를 받을지 여부는 y/n만 입력할 수 있습니다.");
    }
}
