package domain.gamer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Nicknames {

    private static final int MAX_PLAYER_COUNT = 4;
    private final List<Nickname> nicknames;

    public Nicknames(final List<Nickname> nicknames) {
        validateNicknamesCount(nicknames);
        validateDuplicateName(nicknames);
        this.nicknames = List.copyOf(nicknames);
    }

    private void validateNicknamesCount(final List<Nickname> players) {
        if (players.size() > MAX_PLAYER_COUNT || players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 4명입니다.");
        }
    }

    private void validateDuplicateName(final List<Nickname> players) {
        final Set<Nickname> names = new HashSet<>(players);

        if (names.size() == players.size()) {
            return;
        }

        throw new IllegalArgumentException("닉네임은 중복될 수 없습니다.");
    }

    public List<Nickname> getNicknames() {
        return nicknames;
    }
}
