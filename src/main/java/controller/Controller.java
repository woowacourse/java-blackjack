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
	private static final String YES = "Y";

	public static void run() {
		BlackjackGame blackjackGame = initialize();
		progress(blackjackGame);
		end(blackjackGame);
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
		while (blackjackGame.getDealer().canDraw()) {
			OutputView.printDealerDraw();
			blackjackGame.drawDealer();
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
		return !player.isBust() && YES.equalsIgnoreCase(InputView.inputMoreCard(PlayerDto.from(player)));
	}

	private static void end(BlackjackGame blackjackGame) {
		OutputView.printResult(BlackjackGameDto.from(blackjackGame));
		OutputView.printMatchResult(BlackjackGameDto.from(blackjackGame));
	}
}
