package blackjack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import blackjack.domain.Dealer;
import blackjack.domain.DealerDrawable;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Outcome;
import blackjack.domain.Player;
import blackjack.domain.Role;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTurnDto;
import blackjack.dto.TableStatusDto;

public class BlackJackService {

	private List<Role> players;
	private Role dealer;
	private Deck deck;
	private ListIterator<Role> it;
	private Role currentPlayer;

	public void initBlackJack() {
		deck = new Deck();
		dealer = new Dealer(new Hand(), DealerDrawable::chooseDraw);
	}

	public void joinPlayers(final List<String> names) {
		players = names.stream()
			.map(name -> new Player(name, new Hand()))
			.collect(Collectors.toList());
		initPlayerIterator();
	}

	private void initPlayerIterator() {
		it = players.listIterator();
		nextPlayer();
	}

	public TableStatusDto distributeCardToDealer() {
		dealer.draw(deck.draw());
		final TableStatusDto dealerStatus = TableStatusDto.from(dealer);
		dealer.draw(deck.draw());
		return dealerStatus;
	}

	public List<TableStatusDto> distributeCardToPlayers() {
		List<TableStatusDto> playerStatuses = new ArrayList<>();
		for (Role player : players) {
			player.draw(deck.draw());
			player.draw(deck.draw());
			playerStatuses.add(TableStatusDto.from(player));
		}
		return playerStatuses;
	}

	public PlayerTurnDto whoseTurn() {
		return PlayerTurnDto.from(currentPlayer);
	}

	public PlayerStatusDto drawPlayer(final String answer) {
		if (answer.equals("n")) {
			return PlayerStatusDto.from(false, hasNextPlayer(), currentPlayer);
		}
		currentPlayer.draw(deck.draw());
		return PlayerStatusDto.from(currentPlayer.canDraw(), hasNextPlayer(), currentPlayer);
	}

	private boolean hasNextPlayer() {
		return it.hasNext();
	}

	private void nextPlayer() {
		currentPlayer = it.next();
	}

	public DealerTurnDto drawDealer() {
		if (!dealer.canDraw()) {
			return DealerTurnDto.from(dealer, false, Dealer.CAN_NOT_DRAW_STANDARD);
		}
		dealer.draw(deck.draw());
		return DealerTurnDto.from(dealer, true, Dealer.CAN_DRAW_STANDARD);
	}

	public FinalResultDto calculateFinalResult() {
		for (Role player : players) {
			Outcome outcome = judge(player);
			player.recordCompeteResult(outcome);
			dealer.recordCompeteResult(outcome.getOppositeOutcome());
		}
		return FinalResultDto.from(dealer, players);
	}

	private Outcome judge(Role player) {
		return Outcome.of(player.calculateFinalScore(), dealer.calculateFinalScore());
	}
}
