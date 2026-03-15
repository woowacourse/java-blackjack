package blackjack.model;

import blackjack.exception.ErrorMessage;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {

    public static final int PLAYERS_MAX_SIZE = 5;
    public static final int PLAYERS_MIN_SIZE = 2;
    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayersNumber(players);
        this.players = players;
    }

    public void giveTwoCards(Deck deck) {
        for (Player player : players) {
            player.receiveCard(deck.giveCard());
            player.receiveCard(deck.giveCard());
        }
    }

    public Map<Player, GameResult> judgeAll(Dealer dealer, Referee referee) {
        Map<Player, GameResult> gameResult = new LinkedHashMap<>();
        for (Player player : players) {
            gameResult.put(player, referee.judge(player, dealer));
        }
        return gameResult;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validatePlayersNumber(List<Player> players) {
        if (players.size() < Players.PLAYERS_MIN_SIZE || players.size() > PLAYERS_MAX_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PLAYERS.getMessage());
        }
    }
}
