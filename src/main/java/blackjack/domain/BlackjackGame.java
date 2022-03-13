package blackjack.domain;

import java.util.List;

import blackjack.controller.BlackjackController;

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

    public void turnPlayers(BlackjackController blackjackController) {
        for (Player player : blackjackPlayers.getPlayers()) {
            turnEachPlayerIfGuest(blackjackController, player);
        }
    }

    private void turnEachPlayerIfGuest(BlackjackController blackjackController, Player player) {
        if (player.isDealer(Dealer.NAME)) {
            return;
        }

        while(checkGetMoreCard(player) && blackjackController.receiveForGetMoreCard(player.getName())) {
            addCard(player);
            blackjackController.announcePresentCard(player);
        }
    }

    public boolean checkGetMoreCard(Player player) {
        return !player.isOverLimit(Match.MAX_WINNER_POINT);
    }

    public void turnDealer(BlackjackController blackjackController) {
        Player dealer = blackjackPlayers.getDealer();
        if (!dealer.isOverLimit(Dealer.MAX_POINT)) {
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
                .filter(player -> player.isDealer(Dealer.NAME))
                .findFirst()
                .orElseThrow();
        for (Player player : players.getPlayers()) {
            scoreResultIfGuest(dealer, player, results);
        }
        return results;
    }

    private void scoreResultIfGuest(Player dealer, Player player, Results results) {
        if (player.isDealer(Dealer.NAME)) {
            return;
        }
        scorePlayers(dealer, player, results);
    }

    private void scorePlayers(Player dealer, Player guest, Results results) {
        int playerPoint = guest.getDeck()
                .sumPoints();
        int dealerPoint = dealer.getDeck()
                .sumPoints();

        Match result = Match.findWinner(playerPoint, dealerPoint);
        Match dealerResult = result.getDealerResult();
        results.addResult(dealer, dealerResult);
        results.addResult(guest, result);
    }

    public Players getBlackjackPlayers() {
        return blackjackPlayers;
    }
}
