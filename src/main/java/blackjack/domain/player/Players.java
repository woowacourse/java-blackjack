package blackjack.domain.player;

import blackjack.domain.result.Result;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final Dealer dealer;
    private final List<GamePlayer> gamePlayers;

    public Players(Dealer dealer, List<GamePlayer> gamePlayers) {
        this.dealer = dealer;
        this.gamePlayers = gamePlayers;
    }

    public Result compareResults() {
        Map<Name, Profit> playerResults = new LinkedHashMap<>();
        Profit dealerProfit = new Profit(0);
        playerResults.put(dealer.getName(), dealerProfit);

        for (GamePlayer gamePlayer : gamePlayers) {
            Profit playerProfit = gamePlayer.getProfit(dealer);
            playerResults.put(gamePlayer.getName(), playerProfit);
            dealerProfit = dealerProfit.subtract(playerProfit);
        }
        playerResults.put(dealer.getName(), dealerProfit);

        return new Result(playerResults);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        gamePlayers.forEach(players::add);

        return players;
    }
}
