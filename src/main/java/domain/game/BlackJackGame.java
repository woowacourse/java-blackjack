package domain.game;

import domain.betting.Bets;
import domain.betting.Money;
import domain.betting.ProfitRate;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import domain.player.PlayerNames;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackJackGame {

    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer;

    public BlackJackGame(final PlayerNames playerNames, final Dealer dealer) {
        this.dealer = dealer;

        playerNames.names().forEach(name -> {
            Player player = new Player(name, dealer.dealHand());
            this.players.add(player);
        });
    }

    public void hitPlayer(final Player targetPlayer) {
        if (targetPlayer.isHittable()) {
            targetPlayer.hit(dealer.dealCard());
        }
    }

    public boolean hitDealer() {
        if (dealer.isHittable()) {
            hitPlayer(dealer);
            return true;
        }
        return false;
    }

    public Map<Player, Money> getProfits(final Bets bets) {
        Map<Player, Money> playerProfits = new LinkedHashMap<>();
        Map<Player, Result> playerResults = getGameResults();

        playerResults.forEach((player, result) -> {
                    double rate = ProfitRate.from(result).get();
                    PlayerName playerName = player.getName();
                    playerProfits.put(player, bets.get(playerName).multiply(rate));
                }
        );

        return playerProfits;
    }

    private Map<Player, Result> getGameResults() {
        Map<Player, Result> results = new LinkedHashMap<>();

        players.forEach(player ->
                results.put(player, Result.of(dealer, player))
        );
        return results;
    }

    public Entry<Player, Integer> getDealerProfit(final Bets bets) {
        int playerProfitsSum = getProfits(bets).values().stream().
                mapToInt(Money::getValue)
                .sum();
        return Map.entry(dealer, playerProfitsSum * -1);
    }

    public Map<Player, Score> getScores() {
        Map<Player, Score> scores = new LinkedHashMap<>();

        getEveryParticipants().forEach(player ->
                scores.put(player, player.getScore())
        );

        return scores;
    }

    public List<Player> getEveryParticipants() {
        List<Player> participants = new LinkedList<>(players);
        participants.add(0, dealer);
        return participants;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

}
