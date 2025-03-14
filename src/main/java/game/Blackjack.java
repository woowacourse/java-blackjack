package game;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import card.Deck;
import duel.DuelResult;
import paticipant.Dealer;
import paticipant.Participant;
import paticipant.Player;
import paticipant.Players;
import value.Score;

public class Blackjack {
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

	public void pickCardPlayersIfNotBust(
		final Function<String, Boolean> playerAnswer,
		final Consumer<Player> printPlayerCard
	) {
		players.pickCardPlayersIfNotBust(playerAnswer, printPlayerCard, deck);
	}

	public void pickCardDealerIfNotMax() {
		while (dealer.isPickCard()) {
			dealer.addCards(deck.pickCards(1));
		}
	}

	public Score calculateScore(final Participant participant) {
		return participant.calculateAllScore();
	}

	public void duel() {
		players.duelVsDealer(this::duelDealerVsPlayer);
	}

	private void duelDealerVsPlayer(final Player player) {
		final Score playerScore = player.calculateAllScore();
		final Score dealerScore = dealer.calculateAllScore();
		if (playerScore.equals(dealerScore) || (player.isBust() && dealer.isBust())) {
			draw(player);
			return;
		}
		if (player.isBust()) {
			dealerWin(player);
			return;
		}
		if (dealer.isBust() || playerScore.isGreaterThan(dealerScore)) {
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
