package blackjack;

import blackjack.domain.Answer;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	private static BlackJackGame initBlackJackGame(Deck deck) {
		BlackJackGame blackJackGame = new BlackJackGame(new Dealer(), createGamers());
		blackJackGame.setInitialCards(deck);
		OutputView.printOpenCards(blackJackGame.getGamers(), blackJackGame.getDealer());
		return blackJackGame;
	}

	private static List<Gamer> createGamers() {
		try {
			return inputGamers();
		} catch (IllegalArgumentException exception) {
			OutputView.printErrorMessage(exception.getMessage());
			return createGamers();
		}
	}

	private static List<Gamer> inputGamers() {
		List<String> names = InputView.requestPlayerName();
		checkDuplicateName(names);
		return names.stream()
			.map(Gamer::new)
			.collect(Collectors.toList());
	}

	private static void checkDuplicateName(final List<String> names) {
		Set<String> removalDuplicateNames = new HashSet<>(names);
		if (names.size() != removalDuplicateNames.size()) {
			throw new IllegalArgumentException("[ERROR] 중복된 이름은 입력할 수 없습니다.");
		}
	}

	private static void progressGamerAdditionalCard(Deck deck, Gamer gamer) {
		while (isReceivable(gamer)) {
			gamer.receiveCard(deck.draw());
			OutputView.printGamerCards(gamer);
		}
	}

	private static boolean isReceivable(Gamer gamer) {
		return gamer.isReceivable() && isAnswerYes(gamer.getName());
	}

	private static boolean isAnswerYes(String name) {
		try {
			return Answer.YES == Answer.of(InputView.requestAnswer(name));
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			return isAnswerYes(name);
		}
	}

	private static void progressDealerAdditionalCard(Deck deck, Player dealer) {
		boolean receivable = dealer.isReceivable();
		OutputView.printDealerReceive(receivable);
		if (receivable) {
			dealer.receiveCard(deck.draw());
		}
	}

	private static void printFinalMessage(BlackJackGame blackJackGame) {
		OutputView.printFinalResult(blackJackGame.getDealer(), blackJackGame.getGamers());
		OutputView.printFinalResultBoard(blackJackGame.calculateResultBoard());
	}
}
