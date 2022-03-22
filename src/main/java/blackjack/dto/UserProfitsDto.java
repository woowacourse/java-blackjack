package blackjack.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.money.Money;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public final class UserProfitsDto {

    private final Map<String, Integer> nameProfits;

    public UserProfitsDto(Map<String, Integer> nameProfits) {
        this.nameProfits = nameProfits;
    }

    public static UserProfitsDto of(User dealer, Map<Player, Money> playerProfits) {
        Map<String, Integer> nameProfits = new LinkedHashMap<>();
        nameProfits.put(dealer.getName(), calculateDealerProfit(playerProfits));
        nameProfits.putAll(toPlayerProfitsPrimitive(playerProfits));
        return new UserProfitsDto(nameProfits);
    }

    private static Map<String, Integer> toPlayerProfitsPrimitive(Map<Player, Money> playerProfits) {
        return playerProfits.entrySet()
            .stream()
            .collect(Collectors.toMap(
                entry -> entry.getKey().getName(),
                entry -> entry.getValue().getAmount())
            );
    }

    private static int calculateDealerProfit(Map<Player, Money> playerProfits) {
        return -playerProfits.values()
            .stream()
            .mapToInt(Money::getAmount)
            .sum();
    }

    public Map<String, Integer> getNameProfits() {
        return nameProfits;
    }
}
