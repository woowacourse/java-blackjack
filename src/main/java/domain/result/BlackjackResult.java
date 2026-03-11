package domain.result;

import domain.participant.Bet;
import domain.participant.BetMap;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.Map;

public class BlackjackResult {
    private final ProfitMap playerProfitMap;
    private final BetMap betMap;
    private final DealerMatchCount dealerMatchCount;

    private BlackjackResult(Dealer dealer, Players players, BetMap betMap) {
        this.playerProfitMap = new ProfitMap();
        this.betMap = betMap;
        this.dealerMatchCount = new DealerMatchCount();
        calculateMatchResult(dealer, players);
    }

    public static BlackjackResult from(Dealer dealer, Players players, BetMap betMap) {
        return new BlackjackResult(dealer, players, betMap);
    }

    private void calculateMatchResult(Dealer dealer, Players players) {
        for (Player player : players) {
            determinePlayerResult(dealer, player);
        }
    }

    private void determinePlayerResult(Dealer dealer, Player player) {
        MatchJudge matchJudge = new MatchJudge(dealer, player);
        addMatchResult(player.getName(), matchJudge.judge());
    }

    private void addMatchResult(String playerName, MatchCase matchCase) {
        playerProfitMap.addProfitOf(playerName, calculateProfit(playerName, matchCase));
        matchCase.increaseMatchCountOf(this);
    }

    private Long calculateProfit(String playerName, MatchCase matchCase) {
        return betMap.calculateProfit(playerName, matchCase);
    }

    public void increaseDealerWinCount() {
        this.dealerMatchCount.increaseWinCount();
    }

    public void increaseDrawCount() {
        this.dealerMatchCount.increaseDrawCount();
    }

    public void increaseDealerLoseCount() {
        this.dealerMatchCount.increaseLoseCount();
    }

    public Map<String, Long> getPlayerProfitMap() {
        return playerProfitMap.getMap();
    }

    public long getDealerBenefit() {
        return (-1) * playerProfitMap.sumProfits();
    }
}
