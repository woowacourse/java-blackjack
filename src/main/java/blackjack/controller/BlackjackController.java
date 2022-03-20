package blackjack.controller;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import blackjack.domain.role.Dealer;
import blackjack.domain.role.DealerDrawChoice;
import blackjack.domain.role.Player;
import blackjack.domain.role.PlayerDrawChoice;
import blackjack.domain.role.Role;
import blackjack.domain.role.Roles;
import blackjack.domain.state.Ready;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTurnsDto;
import blackjack.dto.TableDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackController(final InputView inputView, final OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		final BlackjackGame blackjackGame = createBlackjackGame();
		readyToGame(blackjackGame);
		takePlayersTurn(blackjackGame);
		takeDealerTurn(blackjackGame);
		getFinalResult(blackjackGame);
	}

	private BlackjackGame createBlackjackGame() {
		final Role dealer = new Dealer(new Ready(), DealerDrawChoice::chooseDraw);
		final List<Role> players = addPlayers();
		return new BlackjackGame(new Deck(), new Roles(dealer, players));
	}

	private List<Role> addPlayers() {
		final List<String> playerName = inputView.requestPlayerName();
		return playerName.stream()
				.map(name -> new Player(name, new Ready(), getBettingMoney(name)))
				.collect(Collectors.toList());
	}

	private Money getBettingMoney(final String name) {
		final String bettingMoney = inputView.requestBetting(name);
		return new Money(Double.parseDouble(bettingMoney));
	}

	private void readyToGame(final BlackjackGame blackjackGame) {
		List<TableDto> tableDto = blackjackGame.ready();
		outputView.printInitialStatus(tableDto);
	}

	private void takePlayersTurn(final BlackjackGame blackjackGame) {
		PlayerTurnsDto playerTurns = blackjackGame.getPlayerDrawTurn();
		for (String player : playerTurns.getNames()) {
			drawPlayer(blackjackGame, player);
		}
	}

	private void drawPlayer(final BlackjackGame blackjackGame, final String player) {
		String answer = inputView.drawOneMoreCard(player);
		PlayerStatusDto playerStatus = blackjackGame.drawPlayer(PlayerDrawChoice.of(answer), player);
		outputView.printPersonalCards(playerStatus.getTableStatusDto());
		if (!playerStatus.isFinished()) {
			drawPlayer(blackjackGame, player);
		}
	}

	private void takeDealerTurn(final BlackjackGame blackjackGame) {
		outputView.printDealerStatus(blackjackGame.drawDealer());
	}

	private void getFinalResult(final BlackjackGame blackjackGame) {
		outputView.printFinalResult(blackjackGame.calculateFinalResult());
	}
}
