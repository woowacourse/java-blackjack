import java.util.HashMap;
import java.util.Map;

public class Cards {
	private static final int SELECT_ACE_VALUE_STANDARD = 11;
	private static final int ADD_BENEFICIAL_VALUE = 10;
	private static final int BASIC_ACE_VALUE = 1;

	private final Map<String, Integer> cards;

	public Cards() {
		this.cards = new HashMap<>();
	}

	public void addCard(String cardName, int cardValue) {
		this.cards.put(cardName, cardValue);
	}

	public int sum() {
		int sum = this.cards.values().stream()
				.reduce(0, Integer::sum);
		if (this.cards.containsValue(BASIC_ACE_VALUE)) {
			return selectAceValue(sum);
		}
		return sum;
	}

	private int selectAceValue(int sum) {
		if (sum <= SELECT_ACE_VALUE_STANDARD) {
			sum += ADD_BENEFICIAL_VALUE;
		}
		return sum;
	}
}
