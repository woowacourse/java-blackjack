package blackjack.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.view.input.reader.Reader;

public class InputView {

    private final Reader reader;

    public InputView(final Reader reader) {
        this.reader = reader;
    }

    public List<String> requestPlayerNames() {
        final String input = reader.readLine();
        final int limitForSplitAllElement = -1;
        String COMMA_UNIT = ",";
        return Arrays.stream(input.split(COMMA_UNIT, limitForSplitAllElement))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean requestContinuable() {
        final String input = reader.readLine();
        return Choice.from(input).getContinuable();
    }
}
