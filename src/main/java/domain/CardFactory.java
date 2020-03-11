package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *    카드를 생성하는 팩토리 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class CardFactory {
	public static List<Card> create() {
		List<Card> cards = new ArrayList<>();

		for (Symbol symbol : Symbol.values()) {
			createByType(cards, symbol);
		}
		return cards;
	}

	private static void createByType(List<Card> cards, Symbol symbol) {
		for (Type type : Type.values()) {
			cards.add(new Card(type, symbol));
		}
	}
}
