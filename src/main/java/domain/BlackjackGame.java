package domain;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        this.deck = new Deck();
    }

    public void registPlayer(Player player){
        players.add(player);
    }

    public boolean decideDealerHitStand(){
        if(dealer.shouldGetMoreCard()){
            Card card = deck.pull();
            dealer.add(card);
            return true;
        }
        return false;
    }
}
