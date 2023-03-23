package domain.user;

import exception.IllegalBetValueException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PlayerBets {
    private final Map<Name, Integer> playerBets = new LinkedHashMap<>();

    public PlayerBets(List<Name> playerNames, List<Integer> bets) {
        validateBets(bets);
        IntStream.range(0, playerNames.size())
                .forEach(index -> playerBets.put(playerNames.get(index), bets.get(index)));
    }

    public int getBetByName(Name name) {
        return playerBets.get(name);
    }

    private void validateBets(List<Integer> bets) {
        if (countNotDividedByThousand(bets) > 0) {
            throw new IllegalBetValueException();
        }
    }

    private int countNotDividedByThousand(List<Integer> bets) {
        return (int) bets.stream()
                .filter(bet -> bet % 1000 != 0)
                .count();
    }
}
