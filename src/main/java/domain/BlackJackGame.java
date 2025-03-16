package domain;

import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import domain.participant.Players;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Dealer dealer;
    private final Players players;
    private int sequence = 0;

    private BlackJackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJackGame init(Dealer dealer, List<String> playerNames, List<Money> monies) {
        Players players = Players.of(playerNames, monies);
        dealer.handoutCards(players);
        return new BlackJackGame(dealer, players);
    }

    public static BlackJackGame of(Dealer dealer, Players players) {
        return new BlackJackGame(dealer, players);
    }

    public void drawCardForCurrentPlayer() {
        if (getThisTurnPlayer().canDraw()) {
            dealer.giveCards(getThisTurnPlayer(), 1);
        }
    }

    public boolean isInProgress() {
        return sequence < players.getPlayers().size();
    }

    public void determineNextTurnPlayer() {
        while (isInProgress() && !getThisTurnPlayer().canDraw()) {
            sequence++;
        }
    }

    public void skipThisTurn() {
        sequence++;
        determineNextTurnPlayer();
    }

    public Player getThisTurnPlayer() {
        return players.getPlayer(sequence);
    }

    public Map<Player, GameResult> calculateGameResult() {
        return dealer.getGameResult(players);
    }

    public Map<Player, Money> calculateRevenue() {
        return dealer.getPlayersRevenue(players);
    }

    public Map<GameResult, Integer> calculateDealerWinningCount() {
        Map<Player, GameResult> playerResult = calculateGameResult();
        Map<GameResult, Integer> dealerWinningCount = new HashMap<>();
        playerResult.values()
                .stream()
                .map(GameResult::getReverse)
                .forEach((result) -> dealerWinningCount.put(result, dealerWinningCount.getOrDefault(result, 0) + 1));

        return dealerWinningCount;
    }

    public Money calculateDealerRevenue() {
        Map<Player, Money> results = calculateRevenue();
        return results.values()
                .stream()
                .reduce(Money::plus)
                .map(money -> money.times(-1))
                .orElse(Money.of(0));
    }

    public int calculateDealerDrawCount() {
        return dealer.getNewCardCount();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
