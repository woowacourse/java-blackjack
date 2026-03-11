package domain.betting.manager;

import domain.betting.BettingRate;
import domain.betting.policy.*;
import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// THINK Has state and method -> SingleTone?
public class BettingPolicyManager {

    List<BettingPolicy> policies;

    public BettingPolicyManager() {
        this.policies = Stream.of(
                        new BlackjackPushPolicy(),
                        new BlackjackPolicy(),
                        new DoubleBustPolicy(),
                        new BustPolicy(),
                        new ComparePolicy()
                ).sorted()
                .toList();
    }

    public List<BettingPolicy> getPolicies() {
        return policies;
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
