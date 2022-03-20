package blackjack.model.player;

import blackjack.model.Betting;
import blackjack.model.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Gamers {

    private final List<Gamer> gamers;

    public Gamers(Map<String, Betting> gamerInfo, CardDeck cardDeck) {
        gamers = gamerInfo.keySet().stream()
                .map(name -> createGamer(name, cardDeck, gamerInfo.get(name)))
                .collect(Collectors.toList());
    }

    private Gamer createGamer(String name, CardDeck cardDeck, Betting betting) {
        return new Gamer(name, List.of(cardDeck.selectCard(), cardDeck.selectCard()), betting);
    }

    public List<Gamer> getGamers() {
        return new ArrayList<>(gamers);
    }

    public Gamer findGamerByName(String name) {
        return gamers.stream()
                .filter(gamer -> gamer.getName().equals(name))
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }
}
