package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String COMMA = ",";

    private InputView() {

    }

    public static List<String> inputName() {
        return Arrays.stream(scanner.nextLine().split(COMMA)).collect(Collectors.toList());
    }

    public static double inputMoney() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (IllegalArgumentException e){
            OutputView.printError("유효하지 않은 배팅 금액입니다.");
            return inputMoney();
        }
    }

    public static String inputAnswer() {
        String answer = scanner.nextLine();
        if (!answer.equals(YES) && !answer.equals(NO)) {
            OutputView.printError("유효한 대답이 아닙니다.");
            return inputAnswer();
        }
        return answer;
    }
}
