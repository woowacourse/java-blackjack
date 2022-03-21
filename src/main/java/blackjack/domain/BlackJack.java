package blackjack.domain;

import java.util.List;
import java.util.Map;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJack {

	public static final String BUST_MESSAGE = "파산";
	public static final int BUST = 0;
	public static final int BUST_SCORE = 0;
	public static final int OPTIMIZED_WINNING_NUMBER = 21;

	private Deck deck;
	private Participants participants;

	public void startGame() {
		initBlackJack();
		final List<String> playersName = InputView.requestPlayerName();
		joinPlayers(InputView.betMoney(playersName));
		distributeCard();
		redrawCard();
		calculateFinalResult();
	}

	private void initBlackJack() {
		participants = new Participants();
		deck = new Deck();
		participants.initDealer();
	}

	private void joinPlayers(final Map<String, Integer> playersNameAndBettingAmount) {
		participants.joinPlayers(playersNameAndBettingAmount);
	}

	private void distributeCard() {
		final Participant dealer = participants.distributeCardToDealer(deck);
		final List<Participant> players = participants.distributeCardToPlayers(deck);
		OutputView.printInitialStatus(dealer, players);
	}

	private void redrawCard() {
		String currentPlayer = participants.getCurrentPlayerName();
		while (!hasMorePlayer(currentPlayer)) {
			final String answer = InputView.drawOneMoreCard(currentPlayer);
			final Participant playerStatus = participants.drawPlayer(deck, RedrawChoice.of(answer), currentPlayer);
			OutputView.printPersonalHand(playerStatus);
			currentPlayer = participants.getCurrentPlayerName();
		}
		OutputView.printDealerStatus(participants.drawDealer(deck));
	}

	private boolean hasMorePlayer(final String player) {
		return player.isEmpty();
	}

	private void calculateFinalResult() {
		OutputView.printFinalResult(participants.calculateResult());
	}

}
