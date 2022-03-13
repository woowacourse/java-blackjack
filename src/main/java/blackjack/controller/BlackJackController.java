package blackjack.controller;

import java.util.List;

import blackjack.domain.RedrawChoice;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerTurnDto;
import blackjack.dto.TableStatusDto;
import blackjack.service.BlackJackService;

public class BlackJackController {

	private final BlackJackService blackJackService;

	public BlackJackController(final BlackJackService blackJackService) {
		this.blackJackService = blackJackService;
	}

	public void addPlayers(List<String> names) {
		blackJackService.initBlackJack();
		blackJackService.joinPlayers(names);
	}

	public TableStatusDto distributeCardToDealer() {
		return blackJackService.distributeCardToDealer();
	}

	public List<TableStatusDto> distributeCardToPlayers() {
		return blackJackService.distributeCardToPlayers();
	}

	public PlayerTurnDto getWhoseTurn() {
		return blackJackService.whoseTurn();
	}

	public TableStatusDto drawPlayer(String answer, String name) {
		return blackJackService.drawPlayer(RedrawChoice.of(answer), name);
	}

	public DealerTurnDto drawDealer() {
		return blackJackService.drawDealer();
	}

	public FinalResultDto getFinalResult() {
		return blackJackService.calculateFinalResult();
	}
}
