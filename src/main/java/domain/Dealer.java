package domain;

import java.util.Arrays;
import java.util.List;

public class Dealer extends User {
	private static final int SHOULD_ADD_CARD_POINT = 16;
	private static final int FIRST_CARD_INDEX = 0;
	private static final String NAME = "딜러";

	public Dealer() {
		super(NAME);
	}

	public void giveOneCard(CardDeck cardDeck, User user) {
		user.addCard(cardDeck.drawOne());
	}

	public boolean shouldAddCard() {
		return getScore() <= SHOULD_ADD_CARD_POINT;
	}

	public boolean isWin(User that) {
		return isNotBust() && (that.isBust() || getScore() > that.getScore());
	}

	public List<Card> getFirstCard() {
		return Arrays.asList(getCards().get(FIRST_CARD_INDEX));
	}
}
