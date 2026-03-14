package domain;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {

    public Profits calculateProfits(Participants participants, BettingBoard bettingBoard) {
        Dealer dealer = participants.getDealer();
        Map<Participant, Profit> playerProfits = calculatePlayerProfits(participants.getPlayers(), dealer, bettingBoard);
        Profit dealerProfit = calculateDealerProfit(playerProfits);
        return assembleFinalProfits(dealer, dealerProfit, playerProfits);
    }

    private Map<Participant, Profit> calculatePlayerProfits(Players players, Dealer dealer, BettingBoard bettingBoard) {
        Map<Participant, Profit> playerProfits = new LinkedHashMap<>();
        for (Player player : players) {
            MatchResult result = judge(dealer, player);
            Profit profit = bettingBoard.calculateProfit(player, result.getProfitRate());
            playerProfits.put(player, profit);
        }
        return playerProfits;
    }

    private Profit calculateDealerProfit(Map<Participant, Profit> playerProfits) {
        BigDecimal totalPlayerProfit = playerProfits.values().stream()
                .map(Profit::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Profit(totalPlayerProfit).negate();
    }

    private Profits assembleFinalProfits(Dealer dealer, Profit dealerProfit, Map<Participant, Profit> playerProfits) {
        Map<Participant, Profit> finalProfits = new LinkedHashMap<>();
        finalProfits.put(dealer, dealerProfit);
        finalProfits.putAll(playerProfits);
        return new Profits(finalProfits);
    }

    private MatchResult judge(Dealer dealer, Player player) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return MatchResult.DRAW;
        }
        if (player.isBlackjack()) {
            return MatchResult.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack()) {
            return MatchResult.LOSE;
        }
        if (player.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        return determineByScore(dealer.getScore(), player.getScore());
    }

    private MatchResult determineByScore(Score dealerScore, Score playerScore) {
        int compareResult = playerScore.compareTo(dealerScore);

        if (compareResult > 0) {
            return MatchResult.WIN;
        }
        if (compareResult < 0) {
            return MatchResult.LOSE;
        }
        return MatchResult.DRAW;
    }
}
