package blackjack.service;

import java.util.List;

import blackjack.domain.RedrawChoice;
import blackjack.domain.card.Deck;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerTurnDto;
import blackjack.dto.TableStatusDto;

public class BlackJackService {

	public static final String BUST_MESSAGE = "파산";
	public static final int BUST = 0;
	public static final int OPTIMIZED_WINNING_NUMBER = 21;

	private Deck deck;
	private Roles roles;

	public void initBlackJack() {
		roles = new Roles();
		deck = new Deck();
		roles.initDealer();
	}

	public void joinPlayers(final List<String> names) {
		roles.joinPlayers(names);
	}

	public TableStatusDto distributeCardToDealer() {
		return roles.distributeCardToDealer(deck);
	}

	public List<TableStatusDto> distributeCardToPlayers() {
		return roles.distributeCardToPlayers(deck);
	}

	public PlayerTurnDto whoseTurn() {
		return PlayerTurnDto.from(roles.getCurrentPlayer());
	}

	public TableStatusDto drawPlayer(final RedrawChoice answer, final String name) {
		return roles.drawPlayer(deck, answer, name);
	}

	public DealerTurnDto drawDealer() {
		return roles.drawDealer(deck);
	}

	public FinalResultDto calculateFinalResult() {
		return roles.calculateFinalResult();
	}

}
