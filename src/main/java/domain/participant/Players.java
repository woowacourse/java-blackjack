package domain.participant;

import domain.result.GameResult;
import domain.result.GameResultStatus;
import domain.card.Hand;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players createByNames(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(playerName -> new Player(
                        playerName, Hand.createEmpty()))
                .toList();
        return new Players(players);
    }

    public static Players create(List<Player> players) {
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<String> getAllPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public GameResult judgeAgainstDealer(Dealer dealer) {
        int dealerSum = dealer.calculateCardsSum();
        Map<Player, GameResultStatus> gameResult = players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> GameResultStatus.calculate(
                                dealerSum, player.calculateCardsSum())
                ));
        return new GameResult(gameResult);
    }
}
