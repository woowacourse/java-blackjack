package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CardDeck {
	private final Map<String, Integer> valueOf;

	public CardDeck() {
		valueOf = new HashMap<>();

		for (CardType type : CardType.values()) {
			generateCardWith(type);
		}
	}

	private void generateCardWith(CardType type) {
		for (CardValue value : CardValue.values()) {
			String key = value.getName().concat(type.getName());
			valueOf.put(key, value.getValue());
		}
	}

	public Map<String, Integer> pickCard() {
		String key = pickRandomKey();
		Map<String, Integer> pickedCard = new HashMap<>();
		pickedCard.put(key, valueOf.get(key));
		removeCard(key);
		return pickedCard;
	}

	private String pickRandomKey() {
		List<String> keys = new ArrayList(valueOf.keySet());
		Collections.shuffle(keys);
		return keys.get(0);
	}

	private void removeCard(String key) {
		valueOf.remove(key);
	}
}
