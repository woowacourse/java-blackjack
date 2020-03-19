package domain.gamer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CardsResult {
    private Map<Gamer, Integer> gamersCardResult;

    public CardsResult(Gamers gamers) {
        Dealer dealer = gamers.getDealer();
        this.gamersCardResult = gamers.getPlayers().stream()
                .collect(Collectors.toMap(gamer -> gamer, gamer -> gamer.score.getScore(),
                        (a, b) -> a, LinkedHashMap::new));

        gamersCardResult.put(dealer, dealer.score.getScore());
    }

    public Map<Gamer, Integer> getGamersCardResult() {
        return gamersCardResult;
    }
}
