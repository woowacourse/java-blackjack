package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class InputView {

    private static final String DELIMITER = ",";

    private final BufferedReader bufferedReader;

    public InputView(final BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public List<String> readParticipantNames() {
        final String participantsName = readConsole();

        validateBlank(participantsName);

        return Arrays.asList(participantsName.split(DELIMITER));
    }

    public int readPlayerBetAmount() {
        final String betAmount = readConsole();

        return mapToBetAmount(betAmount);
    }

    public String readDrawCardCommand() {
        final String command = readConsole();

        validateBlank(command);

        return command;
    }

    private void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }
    }

    private int mapToBetAmount(String betAmount) {
        try {
            return Integer.parseInt(betAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.", e);
        }
    }

    private String readConsole() {
        try {
            return bufferedReader.readLine().trim();
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.", e);
        }
    }
}
