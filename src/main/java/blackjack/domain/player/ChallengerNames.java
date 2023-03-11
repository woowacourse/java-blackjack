package blackjack.domain.player;

import blackjack.domain.player.exception.DuplicatedPlayerNameException;
import blackjack.domain.player.exception.InvalidChallengerNumberException;

import java.util.List;
import java.util.stream.Collectors;

public class ChallengerNames {

    private static final int CHALLENGER_MAX_COUNT = 10;
    private static final int CHALLENGER_MIN_COUNT = 1;

    private final List<ChallengerName> challengerNames;

    private ChallengerNames(final List<ChallengerName> challengerNames) {
        this.challengerNames = challengerNames;
    }

    public static ChallengerNames from(final List<String> names) {
        validate(names);
        List<ChallengerName> challengerNames = names.stream()
                .map(ChallengerName::new)
                .collect(Collectors.toUnmodifiableList());
        return new ChallengerNames(challengerNames);
    }

    private static void validate(final List<String> names) {
        validateChallengerCount(names);
        validateDuplicatedNames(names);
    }

    private static void validateChallengerCount(final List<String> names) {
        if (names.size() > CHALLENGER_MAX_COUNT || names.size() < CHALLENGER_MIN_COUNT) {
            throw new InvalidChallengerNumberException();
        }
    }

    private static void validateDuplicatedNames(final List<String> names) {
        long distinctNameCount = names.stream()
                .distinct()
                .count();

        if (names.size() != distinctNameCount) {
            throw new DuplicatedPlayerNameException();
        }
    }

    public List<ChallengerName> getChallengerNames() {
        return challengerNames;
    }
}
