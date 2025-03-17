package participant;

import game.Card;

import java.util.List;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    private Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants initializeSetting(Dealer dealer, Players players) {
        return new Participants(dealer, players);
    }

    public void addOneCardWithDealer(Card card) {
        dealer.addOneCard(card);
    }

    public int sumScoreWithDealer() {
        return dealer.sumCardNumbers();
    }

    public boolean shouldDealerHit() {
        return dealer.shouldDealerHit();
    }

    public Card openOneCardWithDealer() {
        return dealer.openOneCard();
    }

    public List<Card> openCardsWithDealer() {
        return dealer.openCards();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
