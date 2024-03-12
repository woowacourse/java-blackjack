package model.blackjackgame;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import model.player.UpdatedPlayer;

public class BlackjackGame {

    private Dealer dealer;
    private Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void distributeCardsForSetting(Cards cards) {
        List<Card> cardsElement = cards.getCards();
        dealer = dealer.addCards(cardsElement.subList(0, 2));
        players = players.addCards(cardsElement.subList(2, cardsElement.size()));
    }

    public boolean hitForPlayer(Player player, Card card) {
        if (player.isNotBust()) {
            players = players.hit(player, card);
            Player hittedPlayer = new UpdatedPlayer(this, player).player();
            return !checkBust(hittedPlayer);
        }
        return false;
    }

    private boolean checkBust(Player player) {
        return player.isBust();
    }

    public boolean dealerHitsUnderSixteen(Card card) {
        if (dealer.isPossibleAddCard()) {
            dealer = dealer.addCard(card);
            return true;
        }
        return false;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
