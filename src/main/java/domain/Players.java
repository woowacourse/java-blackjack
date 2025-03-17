package domain;

import java.util.List;
import java.util.stream.IntStream;

public class Players {

    private final List<Player> players;

    public Players(List<PlayerName> playerNames, List<BettingMoney> bettingMonies) {
        this.players = IntStream.range(0, playerNames.size())
                .mapToObj(i -> new Player(playerNames.get(i), bettingMonies.get(i)))
                .toList();
    }
    
    public void giveCardsToPlayer(PlayerName playerName, Cards cards) {
        Player selectedPlayer = selectPlayer(playerName);
        selectedPlayer.receiveCards(cards);
    }

    private Player selectPlayer(PlayerName playerName) {
        return players.stream()
                .filter(player -> player.isSameName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("플레이어가 존재하지 않습니다."));
    }

    public boolean isDrawable(PlayerName playerName) {
        Player player = selectPlayer(playerName);
        return player.isDrawable();
    }

    public Cards getPlayerCard(PlayerName playerName) {
        Player selectedPlayer = selectPlayer(playerName);
        return selectedPlayer.getCards();
    }

    public List<Player> getPlayers() {
        return players.stream()
                .map(Player::copyOf)
                .toList();
    }
}
