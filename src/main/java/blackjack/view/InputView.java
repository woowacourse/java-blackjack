package blackjack.view;

import blackjack.view.reader.Reader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private final String COMMA_UNIT = ",";

    private final Reader reader;

    public InputView(final Reader reader) {
        this.reader = reader;
    }

    public List<String> requestPlayerNames() {
        final String input = reader.readLine();
        final int limitForSplitAllElement = -1;
        return Arrays.stream(input.split(COMMA_UNIT, limitForSplitAllElement))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean requestContinuable() {
        final String input = reader.readLine();
        return Choice.from(input).getContinuable();
    }
}
