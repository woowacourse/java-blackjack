package blackjack.controller;

import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import blackjack.domain.role.Dealer;
import blackjack.domain.role.DealerDrawChoice;
import blackjack.domain.role.Player;
import blackjack.domain.role.PlayerDrawChoice;
import blackjack.domain.role.Role;
import blackjack.domain.state.Ready;
import blackjack.dto.DealerTableDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTableDto;
import blackjack.dto.PlayerTurnsDto;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

	private final BlackJackService blackJackService;

	public BlackJackController(final BlackJackService blackJackService) {
		this.blackJackService = blackJackService;
	}

	public void run(InputView inputView, OutputView outputView) {
		initBlackJackGame();
		addPlayers(inputView);
		distributeCard(outputView);
		takePlayersTurn(inputView, outputView);
		takeDealerTurn(outputView);
		getFinalResult(outputView);
	}

	private void initBlackJackGame() {
		blackJackService.initBlackJackGame(new Deck(), new Dealer(new Ready(), DealerDrawChoice::chooseDraw));
	}

	private void addPlayers(final InputView inputView) {
		final List<String> playerName = inputView.requestPlayerName();
		List<Role> players = playerName.stream()
				.map(name -> new Player(name, new Ready(), getBettingMoney(inputView, name)))
				.collect(Collectors.toList());
		blackJackService.joinPlayers(players);
	}

	private Money getBettingMoney(final InputView inputView, final String name) {
		final String bettingMoney = inputView.requestBetting(name);
		return new Money(Double.parseDouble(bettingMoney));
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
