package blackjack.domain.result;

import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResult {

    public final Map<Gamer, ResultStrategy> result;

    private BlackJackResult(final Map<Gamer, ResultStrategy> result) {
        this.result = result;
    }

    public static BlackJackResult init(final Gamers gamers) {
        return new BlackJackResult(createInitialGamerStrategy(gamers));

    }

    private static LinkedHashMap<Gamer, ResultStrategy> createInitialGamerStrategy(Gamers gamers) {
        return gamers.getGamers().stream()
                .collect(Collectors.toMap(gamer -> gamer,
                        gamer -> new Keep()
                        , (e1, e2) -> e1
                        , LinkedHashMap::new));
    }

    public Map<Gamer, ResultStrategy> getResult() {
        return result;
    }
}
