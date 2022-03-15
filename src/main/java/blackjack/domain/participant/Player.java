package blackjack.domain.participant;

public class Player extends Participant {

	public Player(Name name) {
		super(name);
	}

	@Override
	public boolean shouldHit() {
		return false;
	}
}
