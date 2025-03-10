package blackjack.domain.user;

import blackjack.domain.game.GameRule;
import blackjack.exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerStorage {

    private final List<Player> players = new ArrayList<>();

    public void initialize(List<Nickname> nicknames) {
        validatePlayerCount(nicknames);
        validateDuplicatedPlayer(nicknames);
        addPlayers(nicknames);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validatePlayerCount(List<Nickname> nicknames) {
        if (nicknames.isEmpty() || nicknames.size() > GameRule.MAX_PLAYER_COUNT.getValue()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_PLAYER_COUNT.getContent());
        }
    }

    private void validateDuplicatedPlayer(List<Nickname> nicknames) {
        Set<Nickname> notDuplicatedNicknames = new HashSet<>(nicknames);
        if (nicknames.size() != notDuplicatedNicknames.size()) {
            throw new IllegalArgumentException(
                    ExceptionMessage.NOT_ALLOWED_DUPLICATED_PLAYER.getContent());
        }
    }

    private void addPlayers(List<Nickname> nicknames) {
        nicknames.stream()
                .map(Player::new)
                .forEach(players::add);
    }
}
