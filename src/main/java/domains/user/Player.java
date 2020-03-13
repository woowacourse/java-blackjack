package domains.user;

import java.util.Objects;

import domains.card.Deck;

public class Player extends User {
	private static final String YES = "y";
	private static final String NO = "n";

	private PlayerName name;

	public Player(String name, Deck deck) {
		this.name = new PlayerName(name);
		this.hands = new Hands(deck);
	}

	public Player(String name, Hands hands) {
		this.name = new PlayerName(name);
		this.hands = hands;
	}

	public boolean needMoreCard(String answer, Deck deck) {
		checkNullOrEmpty(answer);
		checkYesOrNo(answer);

		if (answer.equals(YES)) {
			hit(deck);
			return true;
		}
		return false;
	}

	private void checkNullOrEmpty(String answer) {
		if (Objects.isNull(answer) || answer.isEmpty()) {
			throw new InvalidPlayerException(InvalidPlayerException.NULL_OR_EMPTY);
		}
	}

	private void checkYesOrNo(String answer) {
		if (answer.equals(YES) || answer.equals(NO)) {
			return;
		}
		throw new InvalidPlayerException(InvalidPlayerException.INVALID_INPUT);
	}

	public String getName() {
		return name.toString();
	}
}
