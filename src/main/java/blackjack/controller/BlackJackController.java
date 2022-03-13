package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.DealerDrawable;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.dto.DealerTableDto;
import blackjack.dto.PlayerTableDto;
import java.util.List;

import blackjack.domain.RedrawChoice;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTurnDto;
import blackjack.service.BlackJackService;

public class BlackJackController {

	private final BlackJackService blackJackService;

	public BlackJackController(final BlackJackService blackJackService) {
		this.blackJackService = blackJackService;
	}

	public void initBlackJackGame() {
		blackJackService.initBlackJackGame(new Deck(), new Dealer(new Hand(), DealerDrawable::chooseDraw));
	}

	public void addPlayers(List<String> names) {
		blackJackService.joinPlayers(names);
	}

	public DealerTableDto distributeCardToDealer() {
		return blackJackService.distributeCardToDealer();
	}

	public List<PlayerTableDto> distributeCardToPlayers() {
		return blackJackService.distributeCardToPlayers();
	}

	public PlayerTurnDto getWhoseTurn() {
		return blackJackService.whoseTurn();
	}

	public PlayerStatusDto drawPlayer(String answer) {
		return blackJackService.drawPlayer(RedrawChoice.of(answer));
	}

	public DealerTurnDto drawDealer() {
		return blackJackService.drawDealer();
	}

	public FinalResultDto getFinalResult() {
		return blackJackService.calculateFinalResult();
	}
}
