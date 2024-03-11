package domain.participant;

import domain.Result;
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

    public static Players from(final List<String> names) {
        validate(names);
        return new Players(mapToPlayers(names));
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

    private static List<Player> mapToPlayers(final List<String> names) {
        return names.stream()
                .map(String::trim)
                .map(name -> new Player(new Name(name), Hands.createEmptyHands()))
                .toList();
    }

    private static void validate(final List<String> names) {
        validateSize(names);
        validateDuplicate(names);
    }

    private static void validateSize(final List<String> names) {
        if (names.size() < MIN_SIZE || MAX_SIZE < names.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 참여자 수입니다.");
        }
    }

    private static void validateDuplicate(final List<String> names) {
        if (names.size() != Set.copyOf(names).size()) {
            throw new IllegalArgumentException("[ERROR] 참여자 이름은 중복될 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return names;
    }
}
