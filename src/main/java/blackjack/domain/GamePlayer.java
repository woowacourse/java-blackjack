package blackjack.domain;

import java.util.List;

public class GamePlayer {
    private final Dealer dealer;
    private final Players players;

    public GamePlayer(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void dealerHit(Card card) {
        dealer.addCard(card);
    }

    public void playerHit(int i, Card card) {
        players.addCardToPlayer(i, card);
    }

    public void initializeGamePlayer(List<Card> cards) {
        initializeDealer(cards.subList(0, 2));
        initializePlayer(cards.subList(2, cards.size()));
    }

    private void initializeDealer(List<Card> cards) {
        for (Card card : cards) {
            dealerHit(card);
        }
    }

    private void initializePlayer(List<Card> cards) {
        for (int i = 0; i < players.getCount(); i++) {
            playerHit(i, cards.get(i * 2));
            playerHit(i, cards.get(i * 2 + 1));
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public int getCount() {
        return players.getCount() + 1;
    }
}
