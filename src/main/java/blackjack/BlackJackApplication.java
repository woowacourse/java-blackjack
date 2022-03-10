package blackjack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import blackjack.domain.Answer;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Deck;
import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

	public static void main(String[] args) {
		Deck deck = Deck.initializeDeck();
		BlackJackGame blackJackGame = initBlackJackGame(deck);
		for (Gamer gamer : blackJackGame.getGamers()) {
			progressGamerAdditionalCard(deck, gamer);
		}
		progressDealerAdditionalCard(deck, blackJackGame.getDealer());
		printFinalMessage(blackJackGame);
	}

	private static BlackJackGame initBlackJackGame(final Deck deck) {
		try {
			BlackJackGame blackJackGame = BlackJackGame.initializeSetting(InputView.requestPlayerName(), deck);
			OutputView.printOpenCards(blackJackGame.getGamers(), blackJackGame.getDealer());
			return blackJackGame;
		}catch (IllegalArgumentException exception){
			OutputView.printErrorMessage(exception.getMessage());
			return initBlackJackGame(deck);
		}
	}

	private static void progressGamerAdditionalCard(final Deck deck, final Gamer gamer) {
		while (isReceivable(gamer)) {
			gamer.receiveCard(deck.draw());
			OutputView.printGamerCards(gamer);
		}
	}

	private static boolean isReceivable(final Gamer gamer) {
		return gamer.isReceivable() && isAnswerYes(gamer.getName());
	}

	private static boolean isAnswerYes(final String name) {
		try {
			return Answer.isYes(InputView.requestAnswer(name));
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			return isAnswerYes(name);
		}
	}

	private static void progressDealerAdditionalCard(final Deck deck, final Player dealer) {
		boolean receivable = dealer.isReceivable();
		OutputView.printDealerReceive(receivable);
		if (receivable) {
			dealer.receiveCard(deck.draw());
		}
	}

	private static void printFinalMessage(final BlackJackGame blackJackGame) {
		OutputView.printFinalResult(blackJackGame.getDealer(), blackJackGame.getGamers());
		OutputView.printFinalResultBoard(blackJackGame.calculateResultBoard());
	}
}
