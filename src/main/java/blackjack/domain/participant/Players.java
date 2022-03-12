package blackjack.domain.participant;

import blackjack.domain.card.CardFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> value;

    public Players(final List<String> names) {
        validateDuplicate(names);

        this.value = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicate(final List<String> names) {
        final long countNoDuplicate = names.stream()
                .distinct()
                .count();

        if (countNoDuplicate != names.size()) {
            throw new IllegalArgumentException("이름은 중복을 허용하지 않습니다.");
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
