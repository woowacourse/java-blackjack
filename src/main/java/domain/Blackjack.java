package domain;

public class Blackjack {
	private static final int BUST_SCORE = 21;
	private static final int DEALER_PICK_CARD_SCORE_MAX = 16;

	public boolean isPickCardByPlayer(final Player player) {
		return player.isPickCard(BUST_SCORE);
	}

	public boolean isPickCardByDealer(final Dealer dealer) {
		return dealer.isPickCard(BUST_SCORE, DEALER_PICK_CARD_SCORE_MAX);
	}

	public int calculateScore(final Participant participant) {
		return participant.calculateAllScore(BUST_SCORE);
	}

	public void duelDealerVsPlayer(final Dealer dealer, final Player player) {
		dealer.startDuel(player, BUST_SCORE);
	}

}
