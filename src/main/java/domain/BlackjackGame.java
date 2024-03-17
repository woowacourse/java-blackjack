package domain;

import dto.GameResult;
import dto.PlayerResult;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    public static final int INITIAL_HAND_SIZE = 2;

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        initGame();
    }

    private void initGame() {
        initPlayers();
        initDealer();
    }

    private void initPlayers() {
        for (final Player player : players.getPlayers()) {
            player.drawCards(dealer.drawCards(INITIAL_HAND_SIZE));
        }
    }

    private void initDealer() {
        dealer.drawCards(dealer.drawCards(INITIAL_HAND_SIZE));
    }

    public void dealCard(final Participant participant) {
        participant.drawCard(dealer.drawSingleCard());
    }

    public GameResult finishGame() {
        final List<PlayerResult> playerResults = new ArrayList<>();
        int totalProfit = 0;
        for (final Player player : players.getPlayers()) {
            final int profit = player.profit(dealer);
            totalProfit += profit;
            System.out.println(player.name() + ": " + profit);
            final PlayerResult playerResult = new PlayerResult(player.name(), profit);
            playerResults.add(playerResult);
        }
        final PlayerResult dealerResult = new PlayerResult(dealer.name(), -totalProfit);
        return new GameResult(playerResults, dealerResult);
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
