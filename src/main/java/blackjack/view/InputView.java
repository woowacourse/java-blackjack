package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }

    public List<String> readPlayerNicknames() {
        return Arrays.stream(readLine().split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    public int readPlayerbetAmount() {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("배팅 금액은 숫자로 입력해주세요.");
        }
    }

    public boolean readUserCommand() {
        return UserCommand.from(readLine()) == UserCommand.YES;
    }
}
