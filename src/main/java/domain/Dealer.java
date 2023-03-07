package domain;

public class Dealer extends User {

	private static final int UNDER_OVER_SCORE = 17;

	public Dealer() {
		super("딜러");
	}

	@Override
	public boolean isHittable() {
		return cards.isUnder(UNDER_OVER_SCORE);
	}

	public Card getOneDealerCard(){
		return cards.getCards().get(0);
	}
}
