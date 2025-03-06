package view.support;

import java.util.List;

public class InputParser {
    private static final String NAME_SEPARATOR = ",";

    public List<String> parsePlayerNames(String rawPlayerNames) {
        String[] playerNames = rawPlayerNames.split(NAME_SEPARATOR);
        return List.of(playerNames);
    }
}
