package domains.user.name;

import domains.user.InvalidPlayerNamesException;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerNames implements Iterable<PlayerName>{
    private static final String DELIMITER = ",";
    private static final int MAXIMUM_NUMBER_OF_PLAYERS = 5;
    private List<PlayerName> playerNames;

    public PlayerNames(String playerNames) {
        this.playerNames = new ArrayList<>();
        checkNullOrEmpty(playerNames);
        List<String> names = splitNames(playerNames);
        checkNumberOfPlayers(names);

        checkDuplication(names);
        for (String name : names) {
            this.playerNames.add(new PlayerName(name));
        }
    }

    private void checkNullOrEmpty(String playerNames) {
        if (Objects.isNull(playerNames) || playerNames.isEmpty()) {
            throw new InvalidPlayerNamesException(InvalidPlayerNamesException.NULL_OR_EMPTY);
        }
    }

    private List<String> splitNames(String playerNames) {
        List<String> names = Arrays.asList(playerNames.split(DELIMITER));
        names = names.stream().map(String::trim).collect(Collectors.toList());
        return names;
    }

    private void checkNumberOfPlayers(List<String> names) {
        if (names.size() > MAXIMUM_NUMBER_OF_PLAYERS) {
            throw new InvalidPlayerNamesException(InvalidPlayerNamesException.OVER_NUMBER_OF_PLAYERS);
        }
    }

    private void checkDuplication(List<String> names) {
        int distinctNameCount = (int) names.stream()
                .distinct()
                .count();

        if (names.size() != distinctNameCount) {
            throw new InvalidPlayerNamesException(InvalidPlayerNamesException.DUPLICATION);
        }
    }

    @Override
    public Iterator<PlayerName> iterator() {
        return playerNames.iterator();
    }
}
