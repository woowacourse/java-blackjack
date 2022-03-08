package blackjack.view;

import blackjack.view.reader.Reader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private final Reader reader;

    public InputView(final Reader reader) {
        this.reader = reader;
    }

    public List<String> requestPlayerNames() {
        String input = reader.readLine();

        return Arrays.stream(input.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toList());
    }


}
