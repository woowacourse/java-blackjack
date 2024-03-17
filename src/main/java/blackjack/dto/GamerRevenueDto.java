package blackjack.dto;

import blackjack.domain.gamer.BlackjackGamer;
import blackjack.domain.gamer.Money;
import java.util.LinkedHashMap;
import java.util.Map;

public class GamerRevenueDto {

    private final Map<String, Long> nameRevenueMap;

    private GamerRevenueDto(Map<String, Long> nameRevenueMap) {
        this.nameRevenueMap = nameRevenueMap;
    }


    public static GamerRevenueDto fromOrderedMap(Map<BlackjackGamer, Money> gamerRevenueMap) {
        Map<String, Long> nameRevenueMap = new LinkedHashMap<>();
        gamerRevenueMap.forEach((gamer, revenue) -> nameRevenueMap.put(gamer.getName().value(), revenue.amount()));

        return new GamerRevenueDto(nameRevenueMap);
    }

    public Map<String, Long> nameRevenueMap() {
        return nameRevenueMap;
    }
}
