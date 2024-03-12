package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Card;

public class Dealer extends BlackjackGamer {

	private static final String DEFAULT_DEALER_NAME = "딜러";

	public Dealer() {
		super(new Name(DEFAULT_DEALER_NAME));
	}

	public Dealer(List<Card> cards) {
		super(new Name(DEFAULT_DEALER_NAME), cards);
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() < BlackjackConstants.DEALER_MINIMUM_VALUE.getValue();
	}

	public GameResult notifyResultToPlayer(Player player) {
		if (player.isBusted()) {
			return GameResult.LOSE;
		}
		if (isBusted()) {
			return getResultWhenDealerOnlyBusted(player);
		}
		return getResultWhenAllNotBusted(player);
	}

	private GameResult getResultWhenDealerOnlyBusted(Player player) {
		if (player.isBlackjack()) {
			return GameResult.BLACKJACK_WIN;
		}
		return GameResult.WIN;
	}

	private GameResult getResultWhenAllNotBusted(Player player) {
		if (isBlackjack()) {
			return getResultWhenDealerBlackjack(player);
		}
		if (player.isBlackjack()) {
			return GameResult.BLACKJACK_WIN;
		}
		return getResultByComparingScore(player);
	}

	private GameResult getResultWhenDealerBlackjack(Player player) {
		if (player.isBlackjack()) {
			return GameResult.DRAW;
		}
		return GameResult.LOSE;
	}

	private GameResult getResultByComparingScore(Player player) {
		int dealerScore = getScore();
		int playerScore = player.getScore();

		if (dealerScore > playerScore) {
			return GameResult.LOSE;
		}
		if (dealerScore == playerScore) {
			return GameResult.DRAW;
		}
		return GameResult.WIN;
	}
}
