package domain.participant;

import constants.ErrorCode;
import domain.Amount;
import domain.Result;
import exception.DuplicatePlayerNameException;
import exception.InvalidPlayersSizeException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class Players {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 8;

    private final List<Player> names;

    public Players(final List<Player> names) {
        this.names = names;
    }

    protected static Players from(final List<String> names) {
        List<Player> players = mapToPlayers(names);
        validate(players);
        return new Players(players);
    }

    public void forEach(Consumer<? super Player> action) {
        names.forEach(action);
    }

    public boolean isAllBust() {
        return names.stream()
                .allMatch(Player::isBust);
    }

    public Map<Player, Result> getPlayersResult(final Dealer dealer) {
        final Map<Player, Result> result = new LinkedHashMap<>();
        for (Player name : names) {
            result.put(name, name.calculateResult(dealer));
        }
        return result;
    }

    public Map<Player, Amount> calculateResult(final Dealer dealer) {
        final Map<Player, Amount> playerAmountMap = new LinkedHashMap<>();

        for (Player player : names) {
            Result gameResult = player.calculateResult(dealer);
            playerAmountMap.put(player, gameResult.apply(player.getBetAmount()));
        }
        return playerAmountMap;
    }

    private static List<Player> mapToPlayers(final List<String> names) {
        return names.stream()
                .map(String::trim)
                .map(name -> new Player(new Name(name), Hands.createEmptyHands()))
                .toList();
    }

    private static void validate(final List<Player> names) {
        validateSize(names);
        validateDuplicate(names);
    }

    private static void validateSize(final List<Player> names) {
        if (names.size() < MIN_SIZE || MAX_SIZE < names.size()) {
            throw new InvalidPlayersSizeException(ErrorCode.INVALID_SIZE);
        }
    }

    private static void validateDuplicate(final List<Player> names) {
        if (names.size() != Set.copyOf(names).size()) {
            throw new DuplicatePlayerNameException(ErrorCode.DUPLICATE_NAME);
        }
    }

    public List<Player> getPlayers() {
        return names;
    }
}
