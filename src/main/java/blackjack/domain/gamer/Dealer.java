package blackjack.domain.gamer;

public class Dealer extends BlackjackGamer {

	public Dealer() {
		super(new Name("딜러"));
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= 16;
	}
}
