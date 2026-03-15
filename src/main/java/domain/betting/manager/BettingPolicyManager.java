package domain.betting.manager;

import domain.betting.BettingRate;
import domain.betting.policy.*;
import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;

// THINK Has state and method -> SingleTone?
public final class BettingPolicyManager {

    private final List<BettingPolicy> policies;

    public BettingPolicyManager() {
        this.policies = List.of(
                new BlackjackPushPolicy(),
                new BlackjackPolicy(),
                new PlayerLosePolicy(),
                new PlayerWinPolicy()
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
