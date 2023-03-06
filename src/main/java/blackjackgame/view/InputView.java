package blackjackgame.view;

import blackjackgame.domain.DrawCommand;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        String inputNames = scanner.nextLine();
        String[] seperatedInputNames = inputNames.split(DELIMITER);
        List<String> playerNames = Arrays.asList(seperatedInputNames);

        return getNamesWithSpaceRemoved(playerNames);
    }

    private List<String> getNamesWithSpaceRemoved(List<String> playerNames) {
        return playerNames.stream()
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public DrawCommand readDrawCommand() {
        return DrawCommand.of(scanner.nextLine());
    }
}
