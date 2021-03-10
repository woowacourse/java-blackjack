package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players = new ArrayList<>();

    public Players(CardDeck cardDeck, Names names, List<Double> moneys) {
        for (int i = 0; i < moneys.size(); i++) {
            players.add(new Player(cardDeck.generateUserDeck(),
                names.getNames().get(i),
                new Money(moneys.get(i))));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
