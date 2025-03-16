package model.gameresult;

import java.util.Map;
import java.util.stream.Collectors;
import model.bettingamount.BettingAmount;
import model.card.Cards;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class GameResults {
    private final Map<String, GameResult> gameResults;

    public GameResults(final Map<String, GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public static GameResults createFromParticipants(final Players players, final Dealer dealer) {
        Cards dealerCards = dealer.getCards();
        return new GameResults(players.getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> GameResult.createFromCards(dealerCards, player.getCards())
                )));
    }

    public GameResult getGameResultByName(final String name) {
        return gameResults.get(name);
    }

    public int calculatePlayerProfit(Player player) {
        GameResult gameResult = getGameResultByName(player.getName());
        BettingAmount bettingAmount = player.getBettingAmount();
        return bettingAmount.getProfitByGameResult(gameResult);
    }

    public int calculateDealerProfit(final Players players) {
        return -1 * players.getPlayers().stream()
                .mapToInt(this::calculatePlayerProfit)
                .sum();
    }
}
