package domain.gamer;

import java.util.LinkedHashMap;
import java.util.Map;

public class CardsResult {
    private final Map<Gamer, Integer> gamersCardResult = new LinkedHashMap<>();

    public CardsResult(Gamers gamers) {
        gamersCardResult.put(gamers.getDealer(), gamers.getDealer().score.getScore());

        gamers.getPlayers()
                .forEach(gamer -> gamersCardResult.put(gamer, gamer.score.getScore()));
    }

    public Map<Gamer, Integer> getGamersCardResult() {
        return gamersCardResult;
    }
}
