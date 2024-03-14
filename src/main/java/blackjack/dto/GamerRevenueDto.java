package blackjack.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.gamer.BlackjackGamer;
import blackjack.domain.gamer.Money;

public record GamerRevenueDto(Map<String, Integer> nameRevenueMap) {

	public static GamerRevenueDto from(Map<BlackjackGamer, Money> gamerRevenueMap) {
		Map<String, Integer> nameRevenueMap = new LinkedHashMap<>();
		gamerRevenueMap.forEach((gamer, revenue) -> nameRevenueMap.put(gamer.getName().value(), revenue.amount()));

		return new GamerRevenueDto(nameRevenueMap);
	}
}
