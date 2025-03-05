package domain;

import domain.card.CardGroup;
import domain.gamer.Dealer;
import domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;

    public GameManager(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static GameManager creat() {
        final CardGroup cardGroup = new CardGroup(new ArrayList<>());
        return new GameManager(new Dealer(cardGroup), new ArrayList<>());
    }
}
