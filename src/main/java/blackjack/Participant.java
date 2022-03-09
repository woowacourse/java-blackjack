package blackjack;

public class Participant {
	private static final String ERROR_MESSAGE_EMPTY_NAME = "[ERROR] 이름은 공백일 수 없습니다.";
	private static final String ERROR_MESSAGE_UNAVAILABLE_CHARACTER = "[ERROR] 이름에 특수문자가 포함될 수 없습니다.";
	private static final String ALLOWED_CHARACTERS = ".*[^0-9a-zA-Zㄱ-ㅎ가-힣_]+.*";
	private static final String DEALER_NAME = "딜러";

	private final String name;
	private final Cards cards;

	private Participant(String name) {
		this.name = name;
		this.cards = new Cards();
	}

	public static Participant createDealer() {
		return new Participant(DEALER_NAME);
	}

	public static Participant createPlayer(String input) {
		validateName(input);
		return new Participant(input);
	}

	private static void validateName(String input) {
		checkBlankName(input);
		checkUnavailableName(input);
	}

	private static void checkBlankName(String input) {
		if (input.equals("")) {
			throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_NAME);
		}
	}

	private static void checkUnavailableName(String input) {
		if (input.matches(ALLOWED_CHARACTERS)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_UNAVAILABLE_CHARACTER);
		}
	}

	public void receiveCard(Card card) {
		cards.addCard(card);
	}
}
