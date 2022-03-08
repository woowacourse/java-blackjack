import java.util.HashMap;
import java.util.Map;

public class Cards {
	private final Map<String, Integer> valueOf;

	public Cards() {
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

	public Map<String, Integer> getValueOf() {
		return valueOf;
	}
}
