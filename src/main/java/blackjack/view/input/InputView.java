package blackjack.view.input;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.view.input.reader.Reader;
import blackjack.view.utils.Delimiter;

public class InputView {

    private final Reader reader;

    public InputView(final Reader reader) {
        this.reader = reader;
    }

    public List<String> requestPlayerNames() {
        final String inputLine = reader.readLine();
        final List<String> playerNames = Delimiter.COMMA.splitWith(inputLine);
        return playerNames.stream()
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean requestDrawingCardChoice() {
        final String inputLine = reader.readLine();
        return DrawingCardChoice.isChoiceYes(inputLine);
    }

}
