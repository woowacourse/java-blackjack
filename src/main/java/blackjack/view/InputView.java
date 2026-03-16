package blackjack.view;

import blackjack.util.InputParser;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMA = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> inputPlayerNames() {
        return Arrays.stream(
                scanner.nextLine().split(COMMA)
        ).toList();
    }

    public boolean inputMoreCard() {
        String input = scanner.nextLine();

        if(YES.equals(input)){
            return true;
        }
        if(NO.equals(input)) {
            return false;
        }

        throw new IllegalArgumentException(YES + "또는 " + NO + "만 입력 가능합니다");
    }

    public int inputBetAmount() {
        return InputParser.parseInt(
                scanner.nextLine()
        );
    }
}
