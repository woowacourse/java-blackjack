package model.blackjackgame;

import java.util.List;
import java.util.Objects;
import model.card.Card;
import model.card.Cards;
import model.participants.dealer.Dealer;
import model.participants.player.Player;
import model.participants.player.Players;

public class BlackjackGame {

    private static final int CARDS_SETTING_COUNTS = 2;

    private Dealer dealer;
    private Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void distributeCardsForSetting(Cards cards) {
        List<Card> cardsElement = cards.getCards();
        dealer = dealer.addCards(cardsElement.subList(0, CARDS_SETTING_COUNTS));
        players = players.addCards(cardsElement.subList(CARDS_SETTING_COUNTS, cardsElement.size()));
    }

    public boolean hitForPlayer(Player player, Card card) {
        if (player.isNotBust()) {
            players = players.hit(player, card);
            Player hittedPlayer = updatedPlayer(player);
            return !checkBust(hittedPlayer);
        }
        return false;
    }

    private boolean checkBust(Player player) {
        return player.isBust();
    }

    public boolean hitForDealer(Card card) {
        if (dealer.isPossibleAddCard()) {
            dealer = dealer.addCard(card);
            return true;
        }
        return false;
    }

    public Player updatedPlayer(Player player) {
        return players.getPlayers()
                .stream()
                .filter(updatedPlayer -> Objects.equals(
                        updatedPlayer.getName(), player.getName()))
                .findFirst()
                .orElse(player);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
