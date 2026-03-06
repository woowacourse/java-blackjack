package domain;

import java.util.ArrayList;
import java.util.List;
import view.ResultView;

public class BlackjackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame() {
        this.players = new ArrayList<>();
        this.dealer = new Dealer();
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

    public void giveHand() {
        for (Player player : players) {
            for(int i = 0; i < 2; i++) {
                Card card = deck.pull();
                player.add(card);
            }
        }

        for(int i = 0; i < 2; i++) {
            Card card = deck.pull();
            dealer.add(card);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void addPlayerCard(Player player) {
        Card card = deck.pull();
        player.add(card);
    }

    public void addDealerCard() {
        Card card = deck.pull();
        dealer.add(card);
    }
}
