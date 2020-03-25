package com.blackjack;

import static com.blackjack.view.OutputView.printErrorMessage;

import com.blackjack.controller.BlackjackController;

public class BlackjackApplication {
	public static void main(String[] args) {
		try {
			BlackjackController blackjackController = new BlackjackController();
			blackjackController.run();
		} catch (Exception e) {
			printErrorMessage(e.getMessage());
		}
	}
}
