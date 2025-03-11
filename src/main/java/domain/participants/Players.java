package domain.participants;

import domain.game.GameResult;
import domain.game.GameStatistics;
import domain.card.Card;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<PlayerName> playerNames) {
        validateDuplicate(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
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
                        Gamer::clone,
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

    private Player selectPlayer(PlayerName playerName) {
        return players.stream().filter(player -> player.getPlayerName().equals(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 플레이어는 존재하지 않습니다."));
    }

    private void validateDuplicate(List<PlayerName> playerNames){
        Set<PlayerName> playerNameSet = new HashSet<>(playerNames);
        if(playerNameSet.size() != playerNames.size()){
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
    }
}
