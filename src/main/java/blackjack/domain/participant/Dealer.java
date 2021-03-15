package blackjack.domain.participant;

public class Dealer extends Gamer {
	public static final String NAME_OF_DEALER = "딜러";
	public static final int MAX_OF_RECEIVE_MORE_CARD = 16;

	public Dealer() {
		super(NAME_OF_DEALER);
	}

	@Override
	public boolean canReceiveCard(boolean drawFlag) {
		if (playerState.calculatePoint() <= MAX_OF_RECEIVE_MORE_CARD) {
			playerState = playerState.keepContinue(true);
		}
		if (playerState.calculatePoint() > MAX_OF_RECEIVE_MORE_CARD) {
			playerState = playerState.keepContinue(false);
		}
		return !playerState.isFinished();
	}
}
