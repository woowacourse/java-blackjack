package team.blackjack.domain;

import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Dealer dealer;
    private final List<Player> players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        this.deck = new Deck();
    }

     public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /*public Map<String, List<String>> getAllPlayerCards(){
        for (Player player : players) {
            getPlayerCardInHand()
        }
    }

    private Map<String,List<Card>> getPlayerCardInOneHand(Hand hand) {
        for (Hand hand : player.getHands()) {
            hand.getCards();
        }
    }*/

    public Deck getDeck() {
        return deck;
    }
}
