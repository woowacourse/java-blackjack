package blackjack.dto;

import blackjack.domain.gamer.BlackjackGamer;
import blackjack.domain.gamer.Money;
import java.util.LinkedHashMap;
import java.util.Map;

public record GamerRevenueDto(Map<String, Long> nameRevenueMap) {

    public static GamerRevenueDto from(Map<BlackjackGamer, Money> gamerRevenueMap) {
        Map<String, Long> nameRevenueMap = new LinkedHashMap<>();
        gamerRevenueMap.forEach((gamer, revenue) -> nameRevenueMap.put(gamer.getName().value(), revenue.amount()));

        return new GamerRevenueDto(nameRevenueMap);
    }
}
