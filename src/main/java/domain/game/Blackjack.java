package domain.game;

import java.util.Collections;
import java.util.List;

import domain.card.Deck;
import domain.duel.DuelResult;
import domain.paticipant.Dealer;
import domain.paticipant.Participant;
import domain.paticipant.PickableCard;
import domain.paticipant.Player;
import domain.paticipant.Players;

public class Blackjack {
	private static final int BUST_SCORE = 21;
	private static final int DEALER_PICK_CARD_SCORE_MAX = 16;
	private static final int INIT_PICK_CARD_COUNT = 2;

	private final Players players;
	private final Dealer dealer;
	private final Deck deck;

	public Blackjack() {
		this.players = Players.from(Collections.emptyList());
		this.dealer = new Dealer();
		this.deck = Deck.createShuffledDeck();
	}

	public Blackjack(final Players players, final Dealer dealer, final Deck deck) {
		this.players = players;
		this.dealer = dealer;
		this.deck = deck;
	}

	public static Blackjack from(final List<String> names) {
		return new Blackjack(
			Players.from(names), new Dealer(), Deck.createShuffledDeck()
		);
	}

	public void initPickCard() {
		players.pickCards(deck, INIT_PICK_CARD_COUNT);
		dealer.addCards(deck.pickCards(INIT_PICK_CARD_COUNT));
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

	public Players getPlayers() {
		return players;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public Deck getDeck() {
		return deck;
	}
}
