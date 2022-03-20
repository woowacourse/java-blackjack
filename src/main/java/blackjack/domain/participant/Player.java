package blackjack.domain.participant;

public class Player extends Participant {

	private final BetMoney betMoney;

	public Player(Name name, BetMoney betMoney) {
		super(name);
		this.betMoney = betMoney;
	}

	public Player(Name name) {
		this(name, new BetMoney());
	}

	@Override
	public boolean isFinished() {
		return state.isFinished();
	}

	public void stay() {
		state = state.stay();
	}

	public int getBetMoney() {
		return betMoney.getValue();
	}
}
