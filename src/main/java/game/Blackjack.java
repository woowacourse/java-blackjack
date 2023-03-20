package game;

import domain.Bettings;
import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.Result;

import java.util.ArrayList;
import java.util.List;


public class Blackjack {

    private final Players players;
    private final Dealer dealer;

    public Blackjack(List<Player> playerInput) {
        this.players = new Players(playerInput);
        this.dealer = new Dealer(new Cards(new ArrayList<>()));
    }

    public void hitOrStandByPlayer(Player player, boolean isHit) {
        if (isHit) {
            player.pickCard();
        }
    }

    public void hitOrStandByDealer(Dealer dealer) {
        dealer.pickCard();
    }

    public Result createResult(Players players, Dealer dealer, Bettings bettings) {
        return new Result(players, dealer, bettings);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
