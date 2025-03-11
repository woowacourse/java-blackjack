package domain;

import java.util.List;

import domain.paticipant.Dealer;
import domain.paticipant.Participant;
import domain.paticipant.PickableCard;
import domain.paticipant.Player;

public class Blackjack {
	private static final int BUST_SCORE = 21;
	private static final int DEALER_PICK_CARD_SCORE_MAX = 16;
	private static final int INIT_PICK_CARD_COUNT = 2;

	public void initPickCard(final Dealer dealer, final List<Player> players, final Deck deck) {
		dealer.addCards(deck.pickCards(INIT_PICK_CARD_COUNT));
		for (final Player player : players) {
			player.addCards(deck.pickCards(INIT_PICK_CARD_COUNT));
		}
	}

	public boolean isPickCardByPlayer(final Player player) {
		return player.isPickCard(BUST_SCORE);
	}

	public void pickCard(final PickableCard picker, final Deck deck) {
		picker.addCards(deck.pickCards(1));
	}

	public boolean isPickCardByDealer(final Dealer dealer) {
		return dealer.isPickCard(BUST_SCORE, DEALER_PICK_CARD_SCORE_MAX);
	}

	public int calculateScore(final Participant participant) {
		return participant.calculateAllScore(BUST_SCORE);
	}

	public void duelDealerVsPlayer(final Dealer dealer, final Player player) {
		if (player.isBust(BUST_SCORE)) {
			dealer.writeDuelResult(DuelResult.WIN);
			player.writeDuelResult(DuelResult.LOSE);
			return;
		}
		if (dealer.isBust(BUST_SCORE)
			|| player.calculateAllScore(BUST_SCORE) > dealer.calculateAllScore(BUST_SCORE)) {
			dealer.writeDuelResult(DuelResult.LOSE);
			player.writeDuelResult(DuelResult.WIN);
			return;
		}

		dealer.writeDuelResult(DuelResult.WIN);
		player.writeDuelResult(DuelResult.LOSE);
	}
}
