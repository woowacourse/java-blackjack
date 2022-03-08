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
}
