package game;

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

    public Blackjack(List<String> playerNames) {
        this.players = new Players(playerNames);
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

    public Result createResult(Players players, Dealer dealer) {
        return new Result(players, dealer);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
