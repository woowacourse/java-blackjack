package user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    public static final String DELIMITER = ",";
    List<Player> players;

    public Players(String playerNames) {
        List<String> names = splitNames(playerNames);
        checkDuplication(names);
    }

    private List<String> splitNames(String playerNames) {
        List<String> names = Arrays.asList(playerNames.split(DELIMITER));
        names = names.stream().map(String::trim).collect(Collectors.toList());
        return names;
    }

    private void checkDuplication(List<String> names) {
        int distinctNameCount = (int) names.stream()
                .distinct()
                .count();

        if (names.size() != distinctNameCount) {
            throw new InvalidPlayersException(InvalidPlayersException.DUPLICATION);
        }
    }
}
