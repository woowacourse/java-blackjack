package domain;

import domain.card.CardSet;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameBoard {
    private final Map<Player, CardSet> cardSetOfPlayer;
    private final CardSet gameCardSet;

    public GameBoard(final CardSet gameCardSet, final List<Player> players) {
        this.gameCardSet = gameCardSet;
        this.cardSetOfPlayer = initializeCardSetOfPlayer(players);
    }

    private Map<Player, CardSet> initializeCardSetOfPlayer(final List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        player -> CardSet.generateEmptySet()));
    }
}
