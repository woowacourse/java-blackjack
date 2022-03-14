package blackjack.domain;

import java.util.List;

import blackjack.controller.BlackjackController;
import blackjack.domain.card.CardShuffleMachine;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Match;
import blackjack.domain.result.Results;

public class BlackjackGame {

    private final Cards cards;
    private Players blackjackPlayers;

    public BlackjackGame() {
        this.cards = new Cards(new CardShuffleMachine());
    }

    public void initGames(List<String> playerNames) {
        initPlayers(playerNames);
        initCards();
    }

    private void initPlayers(List<String> playerNames) {
        blackjackPlayers = new Players();
        blackjackPlayers.addPlayer(new Dealer());
        for (String playerName : playerNames) {
            blackjackPlayers.addPlayer(new Guest(playerName));
        }
    }

    private void initCards() {
        for (Player blackjackPlayer : blackjackPlayers.getPlayers()) {
            blackjackPlayer.addCard(cards.assignCard());
            blackjackPlayer.addCard(cards.assignCard());
        }
    }

    public boolean isNotDealerBlackJack() {
        return blackjackPlayers.isDealerBlackJack();
    }

    public void turnPlayers(BlackjackController blackjackController) {
        for (Player player : blackjackPlayers.getPlayers()) {
            turnEachPlayerIfGuest(blackjackController, player);
        }
    }

    private void turnEachPlayerIfGuest(BlackjackController blackjackController, Player player) {
        if (player.isDealer()) {
            return;
        }

        while(checkGetMoreCard(player) && blackjackController.receiveForGetMoreCard(player.getName())) {
            addCard(player);
            blackjackController.announcePresentCard(player);
        }
    }

    public boolean checkGetMoreCard(Player player) {
        return !player.isOverMoreCardLimit();
    }

    public void turnDealer(BlackjackController blackjackController) {
        Player dealer = blackjackPlayers.getDealer();
        if (!dealer.isOverMoreCardLimit()) {
            blackjackController.announceDealerCanGetMoreCard();
            addCard(dealer);
            return;
        }
        blackjackController.announceDealerCantGetMoreCard();
    }

    public void addCard(Player player) {
        player.addCard(cards.assignCard());
    }

    public Results calculateResult(Players players) {
        Results results = new Results();
        Player dealer = players.getPlayers()
                .stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow();
        for (Player player : players.getPlayers()) {
            scoreResultIfGuest(dealer, player, results);
        }
        return results;
    }

    private void scoreResultIfGuest(Player dealer, Player guest, Results results) {
        if (guest.isDealer()) {
            return;
        }
        scorePlayers(dealer, guest, results);
    }

    private void scorePlayers(Player dealer, Player guest, Results results) {
        Match result = Match.findWinner(guest, dealer);
        Match dealerResult = result.getDealerResult();
        results.addResult(dealer, dealerResult);
        results.addResult(guest, result);
    }

    public Players getBlackjackPlayers() {
        return blackjackPlayers;
    }
}
