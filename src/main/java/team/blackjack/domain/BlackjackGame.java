package team.blackjack.domain;

import java.util.HashMap;
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

    public Map<String, List<String>> getAllPlayerCards(){
        final HashMap<String, List<String>> result = new HashMap<>();
        for (Player player : players) {
            result.put(player.getName(), getPlayerCardInAllHand(player));
        }

        return result;
    }

    private List<String> getPlayerCardInAllHand(Player player) {
        return player.getHands().stream()
                .map(Hand::getCards)
                .flatMap(List::stream)
                .map(Card::getCardName)
                .toList();
    }

    public Deck getDeck() {
        return deck;
    }
}
