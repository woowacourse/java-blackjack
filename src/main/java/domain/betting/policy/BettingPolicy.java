package domain.betting.policy;

import domain.analyzer.BettingResult;
import domain.betting.BettingRate;
import domain.gamer.Dealer;
import domain.gamer.Player;

/**
 * - Processing (게임 진행 중)
 * - Game End (게임 종료)
 * - Player BlackJack
 *      Dealer BlackJack -> 1 times
 *      Dealer Bust      -> 1.5 times
 *      Dealer Stop      -> 1.5 times
 * - Player Bust
 *      Dealer Bust      -> 1 times
 *      Dealer Blackjack -> -1 times
 *      Dealer Stop      -> -1 times
 * - Player Stop
 *      Dealer Stop
 *          Compare Win  -> 1 times
 *          Compare Lose -> -1 times
 *
 *  전략 패턴?
 *  상태 패턴?
 * */

public abstract class BettingPolicy implements Comparable<BettingPolicy>{

    protected BettingResult bettingResult;

    public BettingPolicy(BettingResult bettingResult) {
        this.bettingResult = bettingResult;
    }

    public abstract boolean isPolicyApplied(Dealer dealer, Player player);

    public abstract BettingRate getBettingRate(Dealer dealer, Player player);

    @Override
    public int compareTo(BettingPolicy o) {
        return this.bettingResult.policyOrder() - o.bettingResult.policyOrder();
    }

}
