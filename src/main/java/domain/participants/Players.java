package domain.participants;

import domain.card.Card;
import domain.game.BettingStatistics;
import domain.game.GameResult;
import domain.game.GameStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public Players(Map<PlayerName, BettingAmount> playerInfo) {
        this(playerInfo.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue())
                ).toList());
    }

    public void giveCard(PlayerName playerName, List<Card> cards) {
        Player selectedPlayer = selectPlayer(playerName);
        selectedPlayer.addCard(cards);
    }

    public List<Card> getPlayerCard(PlayerName playerName) {
        Player selectedPlayer = selectPlayer(playerName);

        return selectedPlayer.getCards();
    }

    public boolean isDrawable(PlayerName playerName) {
        Player player = selectPlayer(playerName);
        return player.isDrawable();
    }

    public List<PlayerName> getUsernames() {
        return players.stream()
                .map(Player::getPlayerName)
                .toList();
    }

    public Map<PlayerName, Gamer> getPlayersInfo() {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getPlayerName,
                        Gamer::newInstance,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public GameStatistics calculateGameStatistics(Dealer dealer) {
        Map<PlayerName, GameResult> gameResults = players.stream()
                .collect(Collectors.toMap(
                        Player::getPlayerName,
                        player -> player.decideGameResult(dealer),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
        return new GameStatistics(gameResults);
    }

    public BettingStatistics calculateBettingStatistics(Dealer dealer) {
        Map<Player, GameResult> gameResults = players.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        player -> player.decideGameResult(dealer),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
        return BettingStatistics.fromBettingResult(gameResults);
    }

    private Player selectPlayer(PlayerName playerName) {
        return players.stream().filter(player -> player.isSameName(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 플레이어는 존재하지 않습니다."));
    }

}
