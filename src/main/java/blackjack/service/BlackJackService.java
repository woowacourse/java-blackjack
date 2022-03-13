package blackjack.service;

import blackjack.domain.Compete;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.RedrawChoice;
import blackjack.domain.Role;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTurnDto;
import blackjack.dto.TableStatusDto;

public class BlackJackService {

	public static final int BUST = 0;
	public static final int OPTIMIZED_WINNING_NUMBER = 21;
	
	private Deck deck;
	private Role dealer;
	private List<Role> players;
	private ListIterator<Role> it;
	private Role currentPlayer;

	public void initBlackJackGame(Deck deck, Role dealer) {
		this.deck = deck;
		this.dealer = dealer;
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

	public PlayerStatusDto drawPlayer(final RedrawChoice answer) {
		if (answer == RedrawChoice.NO) {
			PlayerStatusDto status = PlayerStatusDto.from(false, hasNextPlayer(), currentPlayer);
			nextPlayer();
			return status;
		}
		currentPlayer.draw(deck.draw());
		PlayerStatusDto status = PlayerStatusDto.from(currentPlayer.canDraw(), hasNextPlayer(), currentPlayer);
		if (!currentPlayer.canDraw()) {
			nextPlayer();
		}
		return status;
	}

	private boolean hasNextPlayer() {
		return it.hasNext();
	}

	private void nextPlayer() {
		if (hasNextPlayer()) {
			currentPlayer = it.next();
		}
	}

	public DealerTurnDto drawDealer() {
		if (!dealer.canDraw()) {
			return DealerTurnDto.from(dealer, false, Dealer.CAN_NOT_DRAW_STANDARD);
		}
		dealer.draw(deck.draw());
		return DealerTurnDto.from(dealer, true, Dealer.CAN_DRAW_STANDARD);
	}

	public FinalResultDto calculateFinalResult() {
		Compete compete = new Compete();
		for (Role player : players) {
			compete.judgeCompete(player, dealer);
		}
		return FinalResultDto.from(dealer, players, compete);
	}
}
