package game;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import card.Deck;
import duel.DuelResult;
import paticipant.Dealer;
import paticipant.Participant;
import paticipant.Player;
import paticipant.Players;
import value.Score;

public class Blackjack {
	private static final Score BUST_SCORE = Score.from(21);
	private static final Score DEALER_PICK_CARD_SCORE_MAX = Score.from(16);
	public static final int INIT_PICK_CARD_COUNT = 2;

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

	public void pickCardPlayersIfNotBust(final Function<String, Boolean> playerAnswer) {
		players.pickCardPlayersIfNotBust(playerAnswer, deck, BUST_SCORE);
	}

	public void pickCardDealerIfNotMax() {
		while (dealer.isPickCard(BUST_SCORE, DEALER_PICK_CARD_SCORE_MAX)) {
			dealer.addCards(deck.pickCards(1));
		}
	}

	public Score calculateScore(final Participant participant) {
		return participant.calculateAllScore(BUST_SCORE);
	}

	public void duel() {
		players.duelVsDealer(this::duelDealerVsPlayer);
	}

	private void duelDealerVsPlayer(final Player player) {
		if (player.calculateAllScore(BUST_SCORE).equals(dealer.calculateAllScore(BUST_SCORE))
			|| (player.isBust(BUST_SCORE) && dealer.isBust(BUST_SCORE))) {
			draw(player);
			return;
		}
		if (player.isBust(BUST_SCORE)) {
			dealerWin(player);
			return;
		}
		if (dealer.isBust(BUST_SCORE)
			|| player.calculateAllScore(BUST_SCORE).isGreaterThan(dealer.calculateAllScore(BUST_SCORE))) {
			playerWin(player);
			return;
		}

		dealerWin(player);
	}

	private void playerWin(Player player) {
		dealer.writeDuelResult(DuelResult.LOSE);
		player.writeDuelResult(DuelResult.WIN);
	}

	private void dealerWin(Player player) {
		dealer.writeDuelResult(DuelResult.WIN);
		player.writeDuelResult(DuelResult.LOSE);
	}

	private void draw(Player player) {
		dealer.writeDuelResult(DuelResult.DRAW);
		player.writeDuelResult(DuelResult.DRAW);
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
