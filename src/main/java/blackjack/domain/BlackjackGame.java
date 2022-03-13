package blackjack.domain;

import java.util.List;

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

    private void initCards() {
        for (Player blackjackPlayer : blackjackPlayers.getPlayers()) {
            blackjackPlayer.addCard(cards.assignCard());
            blackjackPlayer.addCard(cards.assignCard());
        }
    }

    private void initPlayers(List<String> playerNames) {
        blackjackPlayers = new Players();
        blackjackPlayers.addPlayer(new Dealer());
        for (String playerName : playerNames) {
            blackjackPlayers.addPlayer(new Guest(playerName));
        }
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
