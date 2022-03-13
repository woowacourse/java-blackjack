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
		Deck.generateDeck();
		initializeCard(players, dealer);
		progressPlayerTurn(players, dealer);
		progressDealerTurn(players, dealer);
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
		Result result = new Result(players);
		outputView.displayNewLine();
		outputView.displayResult(result, players, dealer);
	}

	private void progressDealerTurn(Players players, Dealer dealer) {
		if (dealer.isHit()) {
			outputView.displayNewLine();
		}
		while (dealer.isHit() && !dealer.isBust() && !players.isAllPlayersBlackJackOrBust()) {
			outputView.displayDealerUnderSevenTeen();
			dealer.addCards(1);
		}
	}

	private void progressPlayerTurn(Players players, Dealer dealer) {
		if (!dealer.isBlackJack()) {
			players.getPlayers().forEach(this::progressOnePlayer);
		}
	}

	private void progressOnePlayer(Player player) {
		while (!player.isBust() && !player.isBlackJack() && decideHitOrStay(player)) {
			player.addCards(1);
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

	private void initializeCard(Players players, Dealer dealer) {
		Deck.shuffleDeck();
		dealer.addCards(2);
		players.addCardToAllPlayers(2);
		outputView.displayFirstDistribution(players, dealer);
		outputView.displayDealerOneCard(dealer);
		for (Player player : players.getPlayers()) {
			outputView.displayAllCard(player);
		}
		outputView.displayNewLine();
	}
}
