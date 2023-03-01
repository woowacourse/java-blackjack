import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String SPLITTER = ",";

    public static List<String> inputPlayerNames() {
        String playerNames = SCANNER.nextLine();
        InputValidator.validateBlank(playerNames);
        return List.of(playerNames.split(SPLITTER, -1));
    }
}
