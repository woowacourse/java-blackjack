package blackjack.domain.participants;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGameReferee;
import blackjack.domain.game.GameResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PlayerGroup {
    private final List<Player> players;

    public PlayerGroup(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public void deal(Deck deck) {
        players.forEach(participant -> participant.hitFrom(deck));
    }

    public Map<Player, Long> calculatePlayersProfit(Dealer dealer) {
        Map<Player, Long> playerProfits = new LinkedHashMap<>();
        players.forEach(player ->
            playerProfits.put(player, calculateProfit(dealer, player)));
        return playerProfits;
    }

    private long calculateProfit(Dealer dealer, Player player) {
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        return player.calculateProfit(result);
    }
}
