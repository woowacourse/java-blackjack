package blackjack.domain.role;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import blackjack.domain.BettingAmount;
import blackjack.domain.BlackJack;
import blackjack.domain.DealerDrawChoice;
import blackjack.domain.Outcome;
import blackjack.domain.RedrawChoice;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

public class Roles {

	private static final String NO_PLAYER = "";

	private List<Role> players;
	private Role dealer;

	public void initDealer() {
		dealer = new Dealer(new Hand(), DealerDrawChoice::chooseDraw);
	}

	public Role distributeCardToDealer(final Deck deck) {
		dealer.draw(deck, 1);
		final Role dealerStatus = new Dealer(dealer.getHand(), DealerDrawChoice::chooseDraw);
		dealer.draw(deck, 1);
		return dealerStatus;
	}

	public List<Role> distributeCardToPlayers(final Deck deck) {
		for (Role player : players) {
			player.draw(deck, 2);
		}
		return players;
	}

	public Role drawDealer(final Deck deck) {
		if (!dealer.canDraw()) {
			dealer.stopDraw();
			return dealer;
		}
		dealer.draw(deck, 1);
		return dealer;
	}

	public void joinPlayers(final Map<String, Integer> playersNameAndBattingAmount) {
		players = playersNameAndBattingAmount.entrySet()
			.stream()
			.map(player -> new Player(player.getKey(), new Hand(), new BettingAmount(player.getValue())))
			.collect(Collectors.toList());
	}

	public Role drawPlayer(final Deck deck, final RedrawChoice answer, final String name) {
		final Role currentPlayer = findPlayer(name);
		if (answer == RedrawChoice.NO) {
			currentPlayer.stopDraw();
			return currentPlayer;
		}
		currentPlayer.draw(deck, 1);
		return currentPlayer;
	}

	private Role findPlayer(final String name) {
		return players.stream()
			.filter(player -> player.getName().equals(name))
			.findFirst()
			.orElseThrow(NoSuchElementException::new);
	}

	public void judgeBlackJack() {
		if (dealer.calculateFinalScore() == BlackJack.OPTIMIZED_WINNING_NUMBER) {
			return;
		}
		for (Role player : players) {
			player.earnAmountByBlackJack();
		}
	}

	public String getCurrentPlayerName() {
		return players.stream()
			.filter(player -> player.canDraw() && player.wantDraw())
			.map(Role::getName)
			.findFirst()
			.orElse(NO_PLAYER);
	}

	public List<Role> calculatePlayerResult() {
		for (Role player : players) {
			final Outcome outcome = judge(player);
			player.distributeBettingAmount(outcome);
		}
		return players;
	}

	public Role calculateDealerResult() {
		for (Role player : players) {
			final Outcome outcome = judge(player);
			dealer.recordCompeteResult(outcome.getCounterpartRoleOutcome(), player);
		}
		return dealer;
	}

	private Outcome judge(final Role player) {
		return Outcome.of(player.calculateFinalScore(), dealer.calculateFinalScore());
	}
}
