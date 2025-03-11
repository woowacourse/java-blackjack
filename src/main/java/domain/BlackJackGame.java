package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.HashMap;
import java.util.Map;

public class BlackJackGame {

    private final Dealer dealer;
    private final Players players;

    private BlackJackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJackGame init(Dealer dealer, Players players) {
        dealer.handoutCards(players);
        return new BlackJackGame(dealer, players);
    }

    public void setupDealerCards() {
        dealer.drawUntilLimit();
    }

    public Map<Player, GameResult> calculateGameResult() {
        return dealer.getGameResult(players);
    }

    public Map<GameResult, Integer> calculateDealerWinningCount() {
        Map<Player, GameResult> playerResult = calculateGameResult();
        Map<GameResult, Integer> dealerWinningCount = new HashMap<>();
        playerResult.values().stream()
                .map(GameResult::getReverse)
                .forEach((result) -> dealerWinningCount.put(result, dealerWinningCount.getOrDefault(result, 0) + 1));

        return dealerWinningCount;
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
