package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Players {

    private static final int PLAYER_COUNT_LOWER_BOUND = 2;
    private static final int PLAYER_COUNT_UPPER_BOUND = 8;

    private final List<Player> value;

    public Players(final List<String> names) {
        validate(names);
        this.value = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validate(final List<String> names) {
        validateDuplicate(names);
        validatePlayerCount(names);
    }

    private void validateDuplicate(final List<String> names) {
        final long distinctNameCount = names.stream()
                .distinct()
                .count();

        if (distinctNameCount != names.size()) {
            throw new IllegalArgumentException("이름은 중복을 허용하지 않습니다.");
        }
    }

    private void validatePlayerCount(final List<String> names) {
        if (names.size() < PLAYER_COUNT_LOWER_BOUND) {
            throw new IllegalArgumentException(PLAYER_COUNT_LOWER_BOUND + "명 이상의 참가자가 필요합니다.");
        }
        if (names.size() > PLAYER_COUNT_UPPER_BOUND) {
            throw new IllegalArgumentException(PLAYER_COUNT_UPPER_BOUND + "명 까지만 참여할 수 있습니다.");
        }
    }

    public void initCards(final Deck deck) {
        value.forEach(player -> player.initCards(deck));
    }

    public Optional<Player> findDrawablePlayer() {
        return value.stream()
                .filter(Player::isHit)
                .findFirst();
    }

    public Player findByName(final String playerName) {
        return value.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow();
    }

    public List<String> getNames() {
        return value.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Player> getValue() {
        return new ArrayList<>(value);
    }
}
