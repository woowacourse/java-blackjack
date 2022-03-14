package blackjack.domain.player;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.CompareResult;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public Map<Player, CompareResult> compareResult(final int dealerPoint) {
        return gamers.stream()
                .collect(toMap(gamer -> gamer,
                        gamer -> CompareResult.findResult(dealerPoint, gamer.calculateResult()),
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public List<Gamer> getGamers() {
        return List.copyOf(gamers);
    }
}
