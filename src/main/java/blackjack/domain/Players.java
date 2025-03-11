package blackjack.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;


    public Players(List<Player> players) {
        this.players = players;
    }

    public void addTwoCards(CardDeck cardDeck) {
        players.forEach((player) -> player.addInitialCards(cardDeck));
    }

    public Map<Player, GameResult> calculateStatistics(Dealer dealer) {
        Map<Player, GameResult> playerResult = new HashMap<>();
        for (Player player : players) {
            GameResult gameResult = GameResult.getPlayerGameResultFrom(dealer, player);
            playerResult.put(player, gameResult);
        }
        return playerResult;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
