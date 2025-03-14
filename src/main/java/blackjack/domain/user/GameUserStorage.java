package blackjack.domain.user;

import blackjack.domain.game.GameRule;
import blackjack.exception.ExceptionMessage;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameUserStorage {

    private List<Player> players;
    private Dealer dealer;

    public GameUserStorage(List<Nickname> nicknames) {
        validatePlayerCount(nicknames);
        validateDuplicatedPlayer(nicknames);
        makePlayers(nicknames);
        makeDealer();
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

    private void makePlayers(List<Nickname> nicknames) {
        players = nicknames.stream()
                .map(Player::new)
                .toList();
    }

    private void makeDealer() {
        dealer = new Dealer();
    }
}
