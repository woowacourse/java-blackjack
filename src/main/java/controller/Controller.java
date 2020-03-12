package controller;

import domain.BlackjackGame;
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
	}

	private static BlackjackGame initialize() {
		try {
			return new BlackjackGame(InputView.inputPlayerName());
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return initialize();
		}
	}
}
