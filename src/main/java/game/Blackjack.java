package game;

import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.PlayerName;
import domain.Players;
import domain.Result;

import java.util.ArrayList;
import java.util.List;


public class Blackjack {

    private final Players players;
    private final Dealer dealer;
    private Result result;

    public Blackjack(List<String> playerNames) {
        this.players = getValidPlayerNames(playerNames);
        this.dealer = new Dealer(new Cards(new ArrayList<>()));
    }

    private Players getValidPlayerNames(List<String> playerNamesInput) {
        List<Player> player = getPlayerList(playerNamesInput);
        return new Players(player);
    }

    private List<Player> getPlayerList(List<String> list) {
        List<Player> player = new ArrayList<>();
        for (String playerName : list) {
            player.add(new Player(new PlayerName(playerName), new Cards(new ArrayList<>())));
        }
        return player;
    }


    public void hitOrStandByPlayer(Player player, boolean isHit) {
        if (isHit) {
            player.pickCard();
        }
    }

    public void hitOrStandByDealer(Dealer dealer) {
        dealerHit(dealer);
    }

    private void dealerHit(Dealer dealer) {
        dealer.pickCard();
    }

    public Result createResult(Players players, Dealer dealer) {
        this.result = new Result(players, dealer);
        return result;
    }

    public Result getResult() {
        return result;
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
