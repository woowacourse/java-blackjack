package blackjack.domain;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import java.util.Collections;
import java.util.List;

public class Players {

    private static final int MINIMUM_PLAYER_SIZE = 1;

    private final List<Player> value;

    public Players(Deck deck, List<String> names) {
        this.value = names.stream()
                .map(name -> new Player(name, deck.getInitCards()))
                .collect(toList());
        validateSize();
    }

    private void validateSize() {
        if (value.size() < MINIMUM_PLAYER_SIZE) {
            throw new IllegalArgumentException("플레이어는 최소 1명입니다.");
        }
    }

    public List<Player> getValue() {
        return Collections.unmodifiableList(value);
    }
}
