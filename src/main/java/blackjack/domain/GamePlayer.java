package blackjack.domain;

import java.util.List;

public class GamePlayer {
    private final Players players;
    private final Dealer dealer;

    public GamePlayer(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void playerHit(int i, Card card) {
        players.addCardToPlayer(i, card);
    }

    public void dealerHit(Card card) {
        dealer.addCard(card);

    }

    public void giveCardToPlayerByIndex(int i, Card card) {
        players.addCardToPlayer(i, card);
    }

    public void giveCardToDealer(Card card) {
        dealer.addCard(card);
    }

    public void initializeGamePlayer(List<Card> cards) {
        initializeDealer(cards.subList(0, 2));
        initializePlayer(cards.subList(2, cards.size()));
    }

    private void initializePlayer(List<Card> cards) {
        for (int i = 0; i < players.count(); i++) {
            players.addCardToPlayer(i, cards.get(i * 2));
            players.addCardToPlayer(i, cards.get(i * 2 + 1));
        }
    }

    private void initializeDealer(List<Card> cards) {
        for (Card card : cards) {
            giveCardToDealer(card);
        }
    }

    public boolean isHitDealer() {
        return dealer.isHit();
    }

    public boolean isHitPlayerByIndex(int i) {
        return players.getPlayerIsHit(i);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public int count() {
        return players.count() + 1;
    }
}
