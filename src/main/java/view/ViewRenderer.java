package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Player;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.result.Result;

public class ViewRenderer {

	private static final Map<Denomination, String> DENOMINATION_TO_STRING = new HashMap<>();
	private static final Map<Suit, String> SUIT_TO_STRING = new HashMap<>();

	static {
		DENOMINATION_TO_STRING.put(Denomination.ACE, "A");
		DENOMINATION_TO_STRING.put(Denomination.TWO, "2");
		DENOMINATION_TO_STRING.put(Denomination.THREE, "3");
		DENOMINATION_TO_STRING.put(Denomination.FOUR, "4");
		DENOMINATION_TO_STRING.put(Denomination.FIVE, "5");
		DENOMINATION_TO_STRING.put(Denomination.SIX, "6");
		DENOMINATION_TO_STRING.put(Denomination.SEVEN, "7");
		DENOMINATION_TO_STRING.put(Denomination.EIGHT, "8");
		DENOMINATION_TO_STRING.put(Denomination.NINE, "9");
		DENOMINATION_TO_STRING.put(Denomination.TEN, "10");
		DENOMINATION_TO_STRING.put(Denomination.KING, "K");
		DENOMINATION_TO_STRING.put(Denomination.QUEEN, "Q");
		DENOMINATION_TO_STRING.put(Denomination.JACK, "J");
		SUIT_TO_STRING.put(Suit.SPADE, "스페이드");
		SUIT_TO_STRING.put(Suit.HEART, "하트");
		SUIT_TO_STRING.put(Suit.DIAMOND, "다이아몬드");
		SUIT_TO_STRING.put(Suit.CLOVER, "클로버");
	}

	private static String stringifyCard(final Card card) {
		return DENOMINATION_TO_STRING.get(card.getDenomination()) + SUIT_TO_STRING.get(card.getSuit());
	}

	public static List<NameCardScoreDto> toNameCardScore(final List<Player> players) {
		final List<NameCardScoreDto> nameCardScores = new ArrayList<>();
		players.forEach(player -> nameCardScores.add(toNameCardScore(player)));
		return nameCardScores;
	}

	public static NameCardScoreDto toNameCardScore(final Player player) {
		String score = Integer.toString(player.getScore());
		return new NameCardScoreDto(player.getName(), toCards(player), score);
	}

	private static List<String> toCards(final Player player) {
		return player.getCards().stream()
			.map(ViewRenderer::stringifyCard)
			.collect(Collectors.toUnmodifiableList());
	}

	public static List<NameProfitDto> toNameProfit(final List<Result> results) {
		List<NameProfitDto> nameProfits = new ArrayList<>();
		for (Result result : results) {
			nameProfits.add(toNameProfit(result));
		}
		return nameProfits;
	}

	public static NameProfitDto toNameProfit(final Result result) {
		return new NameProfitDto(result.getName(), Integer.toString(result.getProfit()));
	}
}
