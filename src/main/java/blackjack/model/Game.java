package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public Game(Deck deck, Dealer dealer, List<Player> players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = new Players(players);
    }

    public void dealInitialCards() {
        for (Player player : players.getPlayers()) {
            dealCard(player);
            dealCard(player);
        }
        dealCard(dealer);
        dealCard(dealer);
    }

    private void dealCard(Participant participant) {
        participant.receiveHand(deck.draw());
    }

    public void hitPlayer(Player player) {
        dealCard(player);
    }

    public boolean hitDealer() {
        if (dealer.canHit()) {
            dealCard(dealer);
            return true;
        }
        return false;
    }

    public Map<Player, MatchResult> judgeMatchResults() {
        Map<Player, MatchResult> results = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            results.put(player, MatchResult.judge(dealer, player));
        }
        return results;
    }

    public Card getDealerVisibleCard() {
        return dealer.getHand().getFirst();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Card> getDealerHand() {
        return List.copyOf(dealer.getHand());
    }

    public int getDealerTotal() {
        return dealer.calculateHandTotal();
    }
}
