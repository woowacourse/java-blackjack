package controller;

import domain.BlackjackGame;
import domain.card.YesOrNo;
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
		return !player.isBust() && getYesOrNo(player).isYes();
	}

	private static YesOrNo getYesOrNo(Player player) {
		try {
			String choice = InputView.inputMoreCard(PlayerDto.from(player));
			return YesOrNo.getChoice(choice);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return getYesOrNo(player);
		}
	}

	private static void progressDealer(BlackjackGame blackjackGame) {
		while (blackjackGame.getDealer().canDraw()) {
			OutputView.printDealerDraw();
			blackjackGame.draw(blackjackGame.getDealer());
		}
	}

	private static void end(BlackjackGame blackjackGame) {
		createGameResult(blackjackGame);
		BlackjackGameDto blackjackGameDto = BlackjackGameDto.from(blackjackGame);
		OutputView.printResult(blackjackGameDto);
		OutputView.printMatchResult(blackjackGameDto);
	}

	private static void createGameResult(BlackjackGame blackjackGame) {
		blackjackGame.createResult();
	}
}
