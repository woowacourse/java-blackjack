package controller;

import domain.BlackjackGame;
import domain.gamer.Player;
import dto.BlackjackGameDto;
import dto.PlayerDto;
import view.InputView;
import view.OutputView;

/**
 *   class controller 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Controller {
	public static void run() {
		BlackjackGame blackjackGame = initialize();
		checkBlackjack(blackjackGame);
		progress(blackjackGame);
	}

	private static BlackjackGame initialize() {
		try {
			BlackjackGame blackjackGame = new BlackjackGame(InputView.inputPlayersName());

			blackjackGame.initialDraw();
			OutputView.printInitial(BlackjackGameDto.from(blackjackGame));
			return blackjackGame;
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return initialize();
		}
	}

	private static void checkBlackjack(BlackjackGame blackjackGame) {
	}

	private static void progress(BlackjackGame blackjackGame) {
		if (blackjackGame.isDealerBlackjack()) {
			OutputView.printDealerBlackjack();
			return;
		}
		for (Player player : blackjackGame.getPlayers()) {
			askMoreCard(player, blackjackGame);
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
		return "Y".equalsIgnoreCase(InputView.inputMoreCard(PlayerDto.from(player))) && !player.isBust();
	}
}
