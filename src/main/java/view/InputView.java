package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static view.message.Message.PARTICIPANT_NAME_INPUT_MESSAGE;

public class InputView {
    private final BufferedReader bufferedReader;

    public InputView() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public <T> T getInputWithRetry(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
            return getInputWithRetry(inputReader);
        }
    }

    public List<String> getParticipantNames() {
        OutputView.print(PARTICIPANT_NAME_INPUT_MESSAGE.getMessage());
        return Arrays.asList(Objects.requireNonNull(readConsole()).split(","));
    }

    private String readConsole() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            OutputView.print(e.getMessage());
        }
        return null;
    }
}
