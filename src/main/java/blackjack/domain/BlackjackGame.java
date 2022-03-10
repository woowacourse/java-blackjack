package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    private final Cards cards;
    private final List<Player> blackjackPlayers;

    public BlackjackGame(List<String> playerNames) {
        this.cards = new Cards(new CardShuffleMachine());
        this.blackjackPlayers = new ArrayList<>();
        blackjackPlayers.add(new Dealer());
        for (String playerName : playerNames) {
            blackjackPlayers.add(new Guest(playerName));
        }
    }

    public List<Player> initGames() {
        for (Player blackjackPlayer : blackjackPlayers) {
            blackjackPlayer.addCard(cards.assignCard());
            blackjackPlayer.addCard(cards.assignCard());
        }
        return blackjackPlayers;
    }

    public void addCard(Player player) {
        player.addCard(cards.assignCard());
    }

    public Results calculateResult(List<Player> players) {
        Results results = new Results();
        Player dealer = players.stream().filter(player -> player.getName().equals("딜러")).findFirst().orElseThrow();
        for (Player player : players) {
            scoreResultIfGuest(dealer, player, results);
        }
        return results;
    }

    private void scoreResultIfGuest(Player dealer, Player player, Results results) {
        if (player.getName().equals("딜러")) {
            return;
        }
        scorePlayers(dealer, player, results);
    }

    private void scorePlayers(Player dealer, Player guest, Results results) {
        int playerPoint = guest.getDeck().sumPoints();
        int dealerPoint = dealer.getDeck().sumPoints();

        Match result = Match.compare(playerPoint, dealerPoint);
        Match dealerResult = result.getDealerResult();
        results.addResult(dealer, dealerResult);
        results.addResult(guest, result);
    }
}
