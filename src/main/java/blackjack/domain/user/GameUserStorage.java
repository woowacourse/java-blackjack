package blackjack.domain.user;

import blackjack.domain.game.GameRule;
import blackjack.domain.value.Nickname;
import blackjack.exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameUserStorage {

    private final List<Player> players;
    private final Dealer dealer;

    public GameUserStorage() {
        players = new ArrayList<>();
        dealer = new Dealer();
    }

    public GameUserStorage(List<Nickname> nicknames) {
        this();
        registerPlayer(nicknames);
    }

    public void registerPlayer(List<Nickname> nicknames) {
        validatePlayerCount(nicknames);
        validateDuplicatedPlayer(nicknames);
        savePlayer(nicknames);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
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

    private void savePlayer(List<Nickname> nicknames) {
        players.clear();
        nicknames.stream()
                .map(Player::new)
                .forEach(players::add);
    }
}
