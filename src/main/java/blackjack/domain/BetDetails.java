package blackjack.domain;

import java.util.Map;

public class BetDetails {
    private final Map<Name, Money> betDetails;

    public BetDetails(Map<Name, Money> betDetails) {
        this.betDetails = betDetails;
    }

    public Map<Name, Money> getBetDetails() {
        return betDetails;
    }
}
