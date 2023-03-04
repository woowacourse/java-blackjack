package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static controller.DrawCardCommand.CARD_DRAW_AGAIN;
import static controller.DrawCardCommand.CARD_DRAW_STOP;
import static view.message.MessageFormatter.DRAW_CARD_CARD_MESSAGE;
import static view.message.MessageFormatter.PARTICIPANT_NAME_INPUT_MESSAGE;

public class InputView {

    private static final String DELIMITER = ",";

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
        OutputView.print(PARTICIPANT_NAME_INPUT_MESSAGE.format());

        return Arrays.asList(readConsole().split(DELIMITER));
    }

    public String getDrawCardCommand(final String name) {
        final String drawCardMessage = System.lineSeparator() +
                DRAW_CARD_CARD_MESSAGE.format(name, CARD_DRAW_AGAIN.getCommand(), CARD_DRAW_STOP.getCommand());

        OutputView.print(drawCardMessage);
        return readConsole();
    }

    private String readConsole() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
