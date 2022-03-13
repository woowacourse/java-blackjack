package blackjack.controller;

import blackjack.domain.Names;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGameController {
	private static final String NO = "n";
	private final InputView inputView;
	private final OutputView outputView;

	public BlackJackGameController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void gameStart() {
		Players players = generatePlayers();
		Dealer dealer = new Dealer();
		Deck deck = new Deck();
		initializeCard(players, dealer, deck);
		progressPlayerTurn(players, deck);
		progressDealerTurn(dealer, deck);
		makeResult(players, dealer);
	}

	private Players generatePlayers() {
		try {
			return new Players(new Names(inputView.inputPlayerNames()));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return generatePlayers();
		}
	}

	private void makeResult(Players players, Dealer dealer) {
		outputView.displayNewLine();
		outputView.displayAllCardAndScore(dealer);
		for (Player player : players.getPlayers()) {
			outputView.displayAllCardAndScore(player);
		}
		Result result = new Result();
		outputView.displayNewLine();
		outputView.displayResult(result, players, dealer);
	}

	private void progressDealerTurn(Dealer dealer, Deck deck) {
		if (dealer.isHit()) {
			outputView.displayNewLine();
		}
		while (dealer.isHit() && !dealer.isBurst()) {
			outputView.displayDealerUnderSevenTeen();
			dealer.addCards(deck, 1);
		}
	}

	private void progressPlayerTurn(Players players, Deck deck) {
		for (Player player : players.getPlayers()) {
			progressOnePlayer(deck, player);
		}
	}

	private void progressOnePlayer(Deck deck, Player player) {
		while (!player.isBurst() && decideHitOrStay(player)) {
			player.addCards(deck, 1);
			outputView.displayAllCard(player);
		}
	}

	private boolean decideHitOrStay(Player player) {
		String decision = inputDecision(player);
		return !decision.equalsIgnoreCase(NO);
	}

	private String inputDecision(Player player) {
		try {
			return inputView.inputYesOrNo(player.getName());
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputDecision(player);
		}
	}

	private void initializeCard(Players players, Dealer dealer, Deck deck) {
		deck.shuffleDeck();
		dealer.addCards(deck, 2);
		players.addCardToAllPlayers(deck, 2);
		outputView.displayFirstDistribution(players, dealer);
		outputView.displayDealerOneCard(dealer);
		for (Player player : players.getPlayers()) {
			outputView.displayAllCard(player);
		}
		outputView.displayNewLine();
	}
}
