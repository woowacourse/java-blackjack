package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String DELIMITER = ",";

    private final BufferedReader bufferedReader;

    public InputView() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public List<String> readParticipantNames() {
        String participantsName = readConsole();

        validateBlank(participantsName);

        return Arrays.asList(participantsName.split(DELIMITER));
    }

    public String readDrawCardCommand() {
        String command = readConsole();

        validateBlank(command);

        return command.trim();
    }

    private String readConsole() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }
    }
}
