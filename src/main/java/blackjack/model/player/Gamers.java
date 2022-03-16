package blackjack.model.player;

import blackjack.model.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gamers {

    private final List<Gamer> gamers;

    public Gamers(List<String> gamerNames, CardDeck cardDeck) {
        gamers = gamerNames.stream()
                .map(name -> createGamer(name, cardDeck))
                .collect(Collectors.toList());
    }

    private Gamer createGamer(String name, CardDeck cardDeck) {
        return new Gamer(name, List.of(cardDeck.selectCard(), cardDeck.selectCard()));
    }

    public List<Gamer> getGamers() {
        return new ArrayList<>(gamers);
    }
}
