package domain;

import domain.card.Card;
import domain.card.CardGenerator;
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

    public static GameManager creat(final CardGenerator cardGenerator, final List<String> playerNames) {
        final Dealer dealer = new Dealer(new CardGroup(generateCards(cardGenerator)));
        final List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            final Player player = new Player(playerName, new CardGroup(generateCards(cardGenerator)));
            players.add(player);
        }
        return new GameManager(dealer, players);
    }

    private static List<Card> generateCards(final CardGenerator cardGenerator) {
        return List.of(cardGenerator.generate(), cardGenerator.generate());
    }
}
