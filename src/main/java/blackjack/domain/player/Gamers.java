package blackjack.domain.player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gamers {

    private final List<Gamer> gamers;

    public Gamers(final List<Gamer> gamers) {
        checkDuplicateName(gamers);
        this.gamers = gamers;
    }

    private void checkDuplicateName(final List<Gamer> gamers) {
        Set<Player> tempSet = new HashSet<>(gamers);
        if (gamers.size() != tempSet.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름은 입력할 수 없습니다.");
        }
    }
}
