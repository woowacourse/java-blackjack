package blackjack.domain.gamer.player;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamer;

import java.util.HashSet;
import java.util.List;

public class Players {
    private static final String NAMES_DUPLICATE_ERROR = "플레이어 이름은 중복될 수 없습니다.";

    private final List<Player> values;

    private Players(List<Player> values) {
        this.values = values;
    }

    public static Players of(List<String> names, Deck deck) {
        validateDuplicate(names);
        return new Players(names.stream()
                .map(name -> new Player(name, deck))
                .toList());
    }

    private static void validateDuplicate(List<String> names) {
        int distinctCount = new HashSet<>(names).size();
        if (names.size() != distinctCount) {
            throw new IllegalArgumentException(NAMES_DUPLICATE_ERROR);
        }
    }

    public List<String> getNames() {
        return values.stream().map(Player::getName).toList();
    }

    public List<Player> get() {
        return values;
    }

    public void deal() {
        values.forEach(Gamer::deal);
    }
}
