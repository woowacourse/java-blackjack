package domains.user;

import java.util.Arrays;
import java.util.Objects;

import domains.card.Deck;
import domains.result.ResultType;
import domains.user.name.PlayerName;

public class Player extends User {
	private static final String YES = "y";
	private static final String NO = "n";

	private PlayerName name;

	public Player(PlayerName name, Hands hands) {
		this.name = name;
		this.hands = hands;
		this.blackJack = hands.isBlackJack();
	}

	public Player(PlayerName name, Deck deck) {
		this(name, new Hands(deck));
	}

	public boolean needMoreCard(Deck deck, String answer) {
		checkNullOrEmpty(answer);
		checkYesOrNo(answer);

		if (YES.equals(answer)) {
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
		if (YES.equals(answer) || NO.equals(answer)) {
			return;
		}
		throw new InvalidPlayerException(InvalidPlayerException.INVALID_INPUT);
	}

	public ResultType checkResultType(Dealer dealer) {
		return Arrays.stream(ResultType.values())
			.filter(resultType -> resultType.getJudgeResultType().apply(this, dealer))
			.findFirst()
			.get();
	}

	public String getName() {
		return name.toString();
	}
}
