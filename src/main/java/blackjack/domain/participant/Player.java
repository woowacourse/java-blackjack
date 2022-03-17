package blackjack.domain.participant;

public class Player extends Participant {

	public Player(Name name) {
		super(name);
	}

	public boolean shouldHit(String input) {
		return input.equals("y");
	}

	@Override
	public int getScore() {
		return super.getCards().sum();
	}
}
