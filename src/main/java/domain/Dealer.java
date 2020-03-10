package domain;

public class Dealer extends User {
	private static final int SHOULD_ADD_CARD_POINT = 16;

	public Dealer() {
		super("딜러");
	}

	public void giveOneCard(CardDeck cardDeck, User user) {
		user.addCard(cardDeck.drawOne());
	}

	public boolean shouldAddCard() {
		return getScore() <= SHOULD_ADD_CARD_POINT;
	}
}
