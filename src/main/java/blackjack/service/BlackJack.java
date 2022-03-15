package blackjack.service;

import java.util.List;

import blackjack.domain.RedrawChoice;
import blackjack.domain.card.Deck;
import blackjack.domain.role.Role;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJack {

	public static final String BUST_MESSAGE = "파산";
	public static final int BUST = 0;
	public static final int OPTIMIZED_WINNING_NUMBER = 21;

	private Deck deck;
	private Roles roles;

	public void startGame() {
		initBlackJack();
		joinPlayers(InputView.requestPlayerName());
		distributeCard();
		redrawCard();
		calculateFinalResult();
	}

	private void initBlackJack() {
		roles = new Roles();
		deck = new Deck();
		roles.initDealer();
	}

	private void joinPlayers(final List<String> names) {
		roles.joinPlayers(names);
	}

	private void distributeCard() {
		final Role dealer = roles.distributeCardToDealer(deck);
		final List<Role> players = roles.distributeCardToPlayers(deck);
		OutputView.printInitialStatus(dealer, players);
	}

	private void redrawCard() {
		String currentPlayer = roles.getCurrentPlayerName();
		do {
			String answer = InputView.drawOneMoreCard(currentPlayer);
			final Role playerStatus = roles.drawPlayer(deck, RedrawChoice.of(answer), currentPlayer);
			OutputView.printPersonalHand(playerStatus);
			currentPlayer = roles.getCurrentPlayerName();
		} while (!currentPlayer.isEmpty());
		OutputView.printDealerStatus(roles.drawDealer(deck));
	}

	private void calculateFinalResult() {
		final List<Role> playersResult = roles.calculatePlayerResult();
		final Role dealerResult = roles.calculateDealerResult();
		OutputView.printFinalResult(dealerResult, playersResult);
	}

}
