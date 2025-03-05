package domain;

import domain.card.CardDeck;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameBoard {
    private final Map<Player, CardDeck> cardSetOfPlayer;
    private final CardDeck gameCardDeck;

    public GameBoard(final CardDeck gameCardDeck, final List<Player> players) {
        this.gameCardDeck = gameCardDeck;
        this.cardSetOfPlayer = initializeCardSetOfPlayer(players);
    }

    private Map<Player, CardDeck> initializeCardSetOfPlayer(final List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        player -> CardDeck.generateEmptySet()));
    }
}
