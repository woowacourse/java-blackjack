package domain;

import static java.util.Collections.unmodifiableMap;

import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.vo.Name;
import domain.vo.Revenue;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackResult {
    private final Map<Name, Revenue> blackjackResult;

    private BlackJackResult(Map<Name, Revenue> blackjackResult) {
        this.blackjackResult = unmodifiableMap(blackjackResult);
    }

    public static BlackJackResult of(Dealer dealer, Gamblers gamblers) {
        return new BlackJackResult(getBlackjackResult(dealer, gamblers));
    }

    private static Map<Name, Revenue> getBlackjackResult(Dealer dealer, Gamblers gamblers) {
        Map<Name, Revenue> blackjackResult = new LinkedHashMap<>();
        Name dealerName = Name.from(dealer.getName());
        blackjackResult.put(dealerName, Revenue.from(0));

        for (Gambler gambler : gamblers.getGamblers()) {
            blackjackResult.put(Name.from(gambler.getName()), Revenue.from(gambler.getRevenue(dealer)));
        }
        blackjackResult.put(dealerName, getDealerRevenue(blackjackResult));

        return blackjackResult;
    }

    private static Revenue getDealerRevenue(Map<Name, Revenue> blackjackResult) {
        return Revenue.from(blackjackResult.values()
                .stream()
                .mapToDouble(revenue -> revenue.getRevenue() * -1)
                .sum());
    }

    public Map<Name, Revenue> getBlackjackResult() {
        return blackjackResult;
    }
}
