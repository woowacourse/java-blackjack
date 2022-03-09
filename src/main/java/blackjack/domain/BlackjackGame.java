package blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Player, String> calculateResult(List<Player> players) {
        int dealerPoint = 0;

        int dealerWin = 0;
        int dealerLose = 0;
        int dealerDraw = 0;
        Map<Player, String> results = new LinkedHashMap<>();
        for (Player player : players) {
            int playerPoint = player.getDeck().sumPoints();
            if (player.getName().equals("딜러")) {
                dealerPoint = playerPoint;
                results.put(player, "");
                continue;
            }
            if (playerPoint > 21) {
                dealerWin++;
                results.put(player, "패");
                continue;
            }
            if (playerPoint > dealerPoint) {
                results.put(player, "승");
                dealerLose++;
                continue;
            }
            if (playerPoint == dealerPoint) {
                results.put(player, "무");
                dealerDraw++;
                continue;
            }
            results.put(player, "패");
            dealerWin++;
        }
        Player dealer = players.get(0);
        results.put(dealer, dealerWin + "승 " + dealerDraw + "무 " + dealerLose + "패");
        return results;
    }
}
