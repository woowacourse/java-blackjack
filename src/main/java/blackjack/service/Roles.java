package blackjack.service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import blackjack.domain.DealerDrawChoice;
import blackjack.domain.Outcome;
import blackjack.domain.RedrawChoice;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.role.Dealer;
import blackjack.domain.role.Player;
import blackjack.domain.role.Role;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerResultDto;

public class Roles {

	private static final int COMPETE_COUNT = 1;
	private static final String NO_PLAYER = "";

	private List<Role> players;
	private Role dealer;

	public void initDealer() {
		dealer = new Dealer(new Hand(), DealerDrawChoice::chooseDraw);
	}

	public Role distributeCardToDealer(final Deck deck) {
		dealer.draw(deck.draw(), 1);
		final Role dealerStatus = new Dealer(dealer.getHand(), DealerDrawChoice::chooseDraw);
		dealer.draw(deck.draw(), 1);
		return dealerStatus;
	}

	public List<Role> distributeCardToPlayers(final Deck deck) {
		for (Role player : players) {
			player.draw(deck.draw(), 2);
		}
		return players;
	}

	public Role drawDealer(final Deck deck) {
		// if (!dealer.canDraw()) {
		// 	return DealerTurnDto.from(dealer, false, Dealer.CAN_NOT_DRAW_STANDARD);
		// }
		// dealer.draw(deck.draw(), 1);
		// return DealerTurnDto.from(dealer, true, Dealer.CAN_DRAW_STANDARD);
		if (!dealer.canDraw()) {
			dealer.stopDraw();
			return dealer;
		}
		dealer.draw(deck.draw(), 1);
		return dealer;
	}

	public void joinPlayers(final List<String> names) {
		players = names.stream()
			.map(name -> new Player(name, new Hand()))
			.collect(Collectors.toList());
	}

	public Role drawPlayer(final Deck deck, final RedrawChoice answer, final String name) {
		final Role currentPlayer = findPlayer(name);
		if (answer == RedrawChoice.NO) {
			currentPlayer.stopDraw();
			return currentPlayer;
		}
		currentPlayer.draw(deck.draw(), 1);
		return currentPlayer;
	}

	private Role findPlayer(final String name) {
		return players.stream()
			.filter(player -> player.getName().equals(name))
			.findFirst()
			.orElseThrow(NoSuchElementException::new);
	}

	public String getCurrentPlayerName() {
		return players.stream()
			.filter(player -> player.canDraw() && player.wantDraw())
			.map(Role::getName)
			.findFirst()
			.orElse(NO_PLAYER);
	}

	public FinalResultDto calculateFinalResult() {
		final Map<Outcome, Integer> dealerResult = new EnumMap<>(Outcome.class);
		final List<PlayerResultDto> playersResult = new ArrayList<>();
		for (Role player : players) {
			Outcome outcome = judge(player);
			final Map<Outcome, Integer> playerResult = recordPlayerCompeteResult(outcome);
			playersResult.add(PlayerResultDto.from(player, playerResult));
			dealerResult.merge(outcome.getCounterpartRoleOutcome(), COMPETE_COUNT, Integer::sum);
		}
		return FinalResultDto.from(dealer, dealerResult, playersResult);
	}

	private Outcome judge(Role player) {
		return Outcome.of(player.calculateFinalScore(), dealer.calculateFinalScore());
	}

	private Map<Outcome, Integer> recordPlayerCompeteResult(final Outcome outcome) {
		final Map<Outcome, Integer> result = new EnumMap<>(Outcome.class);
		result.merge(outcome, COMPETE_COUNT, Integer::sum);
		return result;
	}

}
