package domain.participants;

import domain.message.ExceptionMessage;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Names {

    private static final String DEALER_NAME = "딜러";
    private static final int MIN_PLAYERS_SIZE = 1;
    private static final int MAX_PLAYERS_NUMBER = 4;

    private final List<Name> names;

    private Names(final List<Name> names) {
        validate(names);
        this.names = names;
    }

    public static Names createNames(final List<String> names) {
        List<Name> rawNames = names.stream()
                .map(Name::new)
                .collect(Collectors.toList());
        return new Names(rawNames);
    }

    private void validate(final List<Name> playerNames) {
        validatePlayerNumbers(playerNames);
        validateNameDuplicated(playerNames);
        validateNameNotDealer(playerNames);
    }

    private void validatePlayerNumbers(final List<Name> playerNames) {
        if (playerNames.size() < MIN_PLAYERS_SIZE || MAX_PLAYERS_NUMBER < playerNames.size()) {
            throw new IllegalArgumentException(ExceptionMessage.PLAYER_INVALID_NUMBERS.getMessage());
        }
    }

    private void validateNameDuplicated(final List<Name> playerNames) {
        boolean isNamesDuplicated = playerNames.stream()
                .distinct()
                .count() != playerNames.size();

        if (isNamesDuplicated) {
            throw new IllegalArgumentException(ExceptionMessage.PLAYER_NAME_NOT_DUPLICATED.getMessage());
        }
    }

    private void validateNameNotDealer(final List<Name> playerNames) {
        boolean isPlayerNameContainsDealer = playerNames.stream()
                .anyMatch(name -> name.getName().equals(DEALER_NAME));

        if (isPlayerNameContainsDealer) {
            throw new IllegalArgumentException(ExceptionMessage.PLAYER_NAME_NOT_DEALER.getMessage());
        }
    }

    public Stream<Name> stream() {
        return names.stream();
    }

    public int getNamesSize() {
        return names.size();
    }

    public Name findByIndex(final int index) {
        return names.get(index);
    }
}
