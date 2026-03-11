package domain.betting.manager;

import domain.betting.BettingRate;
import domain.betting.policy.*;
import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;

// THINK Has state and method -> SingleTone?
public class BettingPolicyManager {

    List<BettingPolicy> policies;

    public BettingPolicyManager() {
        policies = List.of(
                new BlackjackPushPolicy(),
                new BlackjackPolicy(),
                new DoubleBustPolicy(),
                new BustPolicy(),
                new ComparePolicy()
        );
    }

    public BettingRate gainBettingRate(Dealer dealer, Player player) {
        BettingPolicy bettingPolicy = findBettingPolicy(dealer, player);
        return bettingPolicy.getBettingRate(dealer, player);
    }

    private BettingPolicy findBettingPolicy(Dealer dealer, Player player) {
        return policies.stream()
                .filter(policy -> policy.isPolicyApplied(dealer, player))
                .findFirst()
                .orElse(new BustPolicy());
    }

}
