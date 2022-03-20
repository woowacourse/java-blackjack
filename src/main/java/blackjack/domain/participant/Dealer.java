package blackjack.domain.participant;

public class Dealer extends Participant {

	public static final Name NAME = new Name("딜러");
	public static final int DEALER_FINISHED_SCORE = 16;

	public Dealer(Name name) {
		super(name);
	}

	public Dealer() {
		this(NAME);
	}

	@Override
	public boolean isFinished() {
		return score() > DEALER_FINISHED_SCORE;
	}
}
