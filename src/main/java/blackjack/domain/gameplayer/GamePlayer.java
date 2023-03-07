package blackjack.domain.gameplayer;

import blackjack.domain.card.Card;

import java.util.List;

public class GamePlayer {
    private final Players players;
    private final Dealer dealer;

    public GamePlayer(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void giveCardToDealer(Card card) {
        dealer.addCard(card);
    }

    public void giveCardToPlayerByIndex(int i, Card card) {
        players.addCardToPlayer(i, card);
    }

    public void giveCardTo(Player player, Card card) {
        players.addCardToPlayer(player, card);
    }

    public boolean isHitDealer() {
        return dealer.isHit();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
