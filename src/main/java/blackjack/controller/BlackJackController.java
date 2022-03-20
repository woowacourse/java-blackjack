package blackjack.controller;

import blackjack.domain.game.Deck;
import blackjack.domain.role.Dealer;
import blackjack.domain.role.DealerDrawChoice;
import blackjack.domain.role.PlayerDrawChoice;
import blackjack.domain.state.Ready;
import blackjack.dto.BettingDto;
import blackjack.dto.DealerTableDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTableDto;
import blackjack.dto.PlayerTurnsDto;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

	private final BlackJackService blackJackService;

	public BlackJackController(final BlackJackService blackJackService) {
		this.blackJackService = blackJackService;
	}

	public void run(InputView inputView, OutputView outputView) {
		initBlackJackGame();
		addPlayers(inputView);
		betMoney(inputView);
		distributeCard(outputView);
		takePlayersTurn(inputView, outputView);
		takeDealerTurn(outputView);
		getFinalResult(outputView);
	}

	private void initBlackJackGame() {
		blackJackService.initBlackJackGame(new Deck(), new Dealer(new Ready(), DealerDrawChoice::chooseDraw));
	}

	private void addPlayers(InputView inputView) {
		blackJackService.joinPlayers(inputView.requestPlayerName());
	}

	private void betMoney(InputView inputView) {
		PlayerTurnsDto playerTurns = blackJackService.startBettingPhase();
		List<BettingDto> betting = new ArrayList<>();
		for (String player : playerTurns.getNames()) {
			String money = inputView.drawOneMoreCard(player);
			betting.add(BettingDto.from(player, Double.parseDouble(money)));
		}
		blackJackService.betMoney(betting);
	}

	private void distributeCard(OutputView outputView) {
		DealerTableDto dealerTable = blackJackService.distributeCardToDealer();
		List<PlayerTableDto> playersTable = blackJackService.distributeCardToPlayers();
		outputView.printInitialStatus(dealerTable, playersTable);
	}

	private void takePlayersTurn(InputView inputView, OutputView outputView) {
		PlayerTurnsDto playerTurns = blackJackService.startPlayerDrawPhase();
		for (String player : playerTurns.getNames()) {
			drawPlayer(inputView, outputView, player);
		}
	}

	private void drawPlayer(InputView inputView, OutputView outputView, String player) {
		String answer = inputView.drawOneMoreCard(player);
		PlayerStatusDto playerStatus = blackJackService.drawPlayer(PlayerDrawChoice.of(answer), player);
		outputView.printPlayerHand(playerStatus.getTableStatusDto());
		if (playerStatus.isDraw()) {
			drawPlayer(inputView, outputView, player);
		}
	}

	private void takeDealerTurn(OutputView outputView) {
		outputView.printDealerStatus(blackJackService.drawDealer());
	}

	private void getFinalResult(OutputView outputView) {
		outputView.printFinalResult(blackJackService.calculateFinalResult());
	}
}
