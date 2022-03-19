package blackjack.domain;

import java.util.List;
import java.util.Map;

import blackjack.domain.card.Deck;
import blackjack.domain.role.Role;
import blackjack.domain.role.Roles;
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
		final List<String> playersName = InputView.requestPlayerName();
		joinPlayers(InputView.betMoney(playersName));
		distributeCard();
		redrawCard();
		calculateFinalResult();
	}

	private void initBlackJack() {
		roles = new Roles();
		deck = new Deck();
		roles.initDealer();
	}

	private void joinPlayers(final Map<String, Integer> playersNameAndBattingAmount) {
		roles.joinPlayers(playersNameAndBattingAmount);
	}

	private void distributeCard() {
		final Role dealer = roles.distributeCardToDealer(deck);
		final List<Role> players = roles.distributeCardToPlayers(deck);
		OutputView.printInitialStatus(dealer, players);
	}

	private void redrawCard() {
		String currentPlayer = roles.getCurrentPlayerName();
		while (!hasMorePlayer(currentPlayer)) {
			final String answer = InputView.drawOneMoreCard(currentPlayer);
			final Role playerStatus = roles.drawPlayer(deck, RedrawChoice.of(answer), currentPlayer);
			OutputView.printPersonalHand(playerStatus);
			currentPlayer = roles.getCurrentPlayerName();
		}
		OutputView.printDealerStatus(roles.drawDealer(deck));
	}

	private boolean hasMorePlayer(final String player) {
		return player.isEmpty();
	}

	private void calculateFinalResult() {
		final List<Role> playersResult = roles.calculatePlayerResult();
		final Role dealerResult = roles.calculateDealerResult();
		OutputView.printFinalResult(dealerResult, playersResult);
	}

}
