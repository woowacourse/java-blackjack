package domain.player;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Players {
    private static final int MAX_PLAYER_NUMBER = 8;
    private final List<Player> value;

    public Players(final List<Player> value) {
        validate(value);
        this.value = value;
    }

    public static Players of(final List<String> names, final List<Integer> betAmounts) {
        return new Players(IntStream.range(0, names.size())
                .mapToObj(index -> new Player(new Name(names.get(index)), new BetAmount(betAmounts.get(index))))
                .toList());
    }

    private void validate(final List<Player> players) {
        validatePlayerNumbers(players);
        validateDuplicate(players);
    }

    private void validatePlayerNumbers(final List<Player> players) {
        if (isInvalidPlayersNumber(players)) {
            throw new IllegalArgumentException(String.format("플레이어의 수는 %d명을 초과할 수 없습니다.", MAX_PLAYER_NUMBER));
        }
    }

    private boolean isInvalidPlayersNumber(final List<Player> players) {
        return players.size() > MAX_PLAYER_NUMBER;
    }

    private void validateDuplicate(final List<Player> players) {
        if (hasDuplicatePlayers(players)) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다");
        }
    }

    private boolean hasDuplicatePlayers(final List<Player> players) {
        return Set.copyOf(players).size() != players.size();
    }

    public Stream<Player> stream() {
        return value.stream();
    }

    public double getTotalSum(final Dealer dealer) {
        return getValue().stream()
                .mapToDouble(player -> player.calculateProfit(dealer))
                .sum();
    }

    public List<Player> getValue() {
        return Collections.unmodifiableList(value);
    }
}
