package domain;

public class Dealer extends User {
	private static final int SHOULD_ADD_CARD_POINT = 16;
	private static final int FIRST_CARD_INDEX = 0;
	private static final String NAME = "딜러";

	public Dealer() {
		super(NAME);
	}

	public void giveCard(CardDeck cardDeck, User user) {
		user.addCard(cardDeck.pop());
	}

	public void giveCard(CardDeck cardDeck, Players players) {
		players.forEach(player -> giveCard(cardDeck, player));
	}

	public boolean shouldAddCard() {
		return getScore() <= SHOULD_ADD_CARD_POINT;
	}

	public Card getFirstCard() {
		return getCards().get(FIRST_CARD_INDEX);
	}

	@Override
	public boolean isWin(User that) {
		return that.isBust() || (isNotBust() && isScoreGreaterThan(that.getScore()));
	}
}
