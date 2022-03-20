package blackjack.domain.participant;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Players {

    private static final int MINIMUM_PLAYER_SIZE = 1;

    private final List<Player> value;

    public Players(Map<String, Integer> names) {
        this.value = names.keySet()
                .stream()
                .map(name -> new Player(name, names.get(name)))
                .collect(toList());
        validateSize();
    }

    private void validateSize() {
        if (value.size() < MINIMUM_PLAYER_SIZE) {
            throw new IllegalArgumentException("플레이어는 최소 1명입니다.");
        }
    }

    public void forEach(Consumer<Player> consumer) {
        for (Player player : value) {
            consumer.accept(player);
        }
    }

    public List<Player> getValue() {
        return Collections.unmodifiableList(value);
    }
}
