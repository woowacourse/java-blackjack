package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import blackjack.domain.exceptions.InvalidPlayerException;
import blackjack.domain.result.BettingMoney;

public class PlayerFactory {
    private static final int MAX_PLAYER_NUMBER = 7;

    public static List<Player> create(List<String> playerNames, List<BettingMoney> bettingMoney) {
        validate(playerNames);
        return IntStream.range(0, playerNames.size())
            .mapToObj(index -> new Player(playerNames.get(index), bettingMoney.get(index)))
            .collect(toList());
    }

    private static void validate(List<String> playerNames) {
        validateDuplication(playerNames);
        validateSize(playerNames);
    }

    private static void validateDuplication(List<String> playerNames) {
        long distinctPlayersSize = new HashSet<>(playerNames).size();

        if (playerNames.size() != distinctPlayersSize) {
            throw new InvalidPlayerException(InvalidPlayerException.DUPLICATE_PLAYER);
        }

        if (playerNames.contains(Dealer.NAME)) {
            throw new InvalidPlayerException(InvalidPlayerException.DUPLICATE_DEALER);
        }
    }

    private static void validateSize(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_NUMBER) {
            throw new InvalidPlayerException(InvalidPlayerException.SIZE);
        }
    }

}
