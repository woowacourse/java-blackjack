package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.BlackjackGame;
import domain.GameResult;
import domain.YesOrNo;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.money.Money;
import view.InputView;
import view.OutputView;
import view.dto.BlackjackGameDto;
import view.dto.NameDto;
import view.dto.PlayerDto;

/**
 *   class controller 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Controller {
	public static void run() {
		BlackjackGame blackjackGame = initialize();
		progress(blackjackGame);
		end(blackjackGame);
	}

	private static BlackjackGame initialize() {
		BlackjackGame blackjackGame = new BlackjackGame(initializePlayers());
		blackjackGame.initialDraw();
		OutputView.printInitial(BlackjackGameDto.from(blackjackGame));
		return blackjackGame;
	}

	private static Players initializePlayers() {
		try {
			List<Name> names = initializeNames();
			return new Players(initializePlayersBy(names));
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return initializePlayers();
		}
	}

	private static List<Name> initializeNames() {
		try {
			return InputView.inputPlayersName().stream()
				.map(Name::new)
				.collect(Collectors.toList());
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return initializeNames();
		}
	}

	private static List<Player> initializePlayersBy(List<Name> names) {
		return names.stream()
			.map(name -> new Player(name, askBettingMoney(name)))
			.collect(Collectors.toList());
	}

	private static Money askBettingMoney(Name name) {
		try {
			return Money.of(InputView.inputBettingMoney(NameDto.from(name)));
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return askBettingMoney(name);
		}
	}

	private static void progress(BlackjackGame blackjackGame) {
		if (blackjackGame.isDealerBlackjack()) {
			OutputView.printDealerBlackjack();
			return;
		}
		progressPlayers(blackjackGame);
		progressDealer(blackjackGame);
	}

	private static void progressPlayers(BlackjackGame blackjackGame) {
		for (Player player : blackjackGame.getPlayers()) {
			askMoreCard(player, blackjackGame);
		}
	}

	private static void progressDealer(BlackjackGame blackjackGame) {
		if (blackjackGame.isAllPlayersBust()) {
			return;
		}

		while (blackjackGame.getDealer().canHit()) {
			OutputView.printDealerDraw();
			blackjackGame.draw(blackjackGame.getDealer());
		}
	}

	private static void askMoreCard(Player player, BlackjackGame blackjackGame) {
		if (player.isBlackjack()) {
			return;
		}
		while (isContinue(player)) {
			blackjackGame.draw(player);
			OutputView.printCards(PlayerDto.from(player));
		}
	}

	private static boolean isContinue(Player player) {
		return player.canHit() && inputYesOrNo(player).isYes();
	}

	private static YesOrNo inputYesOrNo(Player player) {
		try {
			return YesOrNo.of(InputView.inputMoreCard(PlayerDto.from(player)));
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return inputYesOrNo(player);
		}
	}

	private static void end(BlackjackGame blackjackGame) {
		OutputView.printCardsResult(BlackjackGameDto.from(blackjackGame));
		OutputView.printGameResult(GameResult.of(blackjackGame));
	}
}
