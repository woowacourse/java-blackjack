package blackjack.domain.participant;

import blackjack.domain.card.CardFactory;
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
        final long countNoDuplicate = names.stream()
                .distinct()
                .count();

        if (countNoDuplicate != names.size()) {
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

    public void prepareGame(final CardFactory cardFactory) {
        value.forEach(player -> player.prepareGame(cardFactory));
    }

    public Optional<Player> findHitPlayer() {
        return value.stream()
                .filter(Player::isHit)
                .findFirst();
    }

    public List<String> getNames() {
        return value.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Player> getValue() {
        return value;
    }
}
