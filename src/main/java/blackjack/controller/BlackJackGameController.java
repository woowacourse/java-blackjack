package blackjack.controller;

import java.util.Map;

import blackjack.domain.user.Dealer;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.result.Result;
import blackjack.domain.result.ResultType;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGameController {

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
			Players players = new Players(inputView.inputPlayerNames());
			return players;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return generatePlayers();
		}
	}

	private void makeResult(Players players, Dealer dealer) {
		outputView.displayAllCardAndScore(dealer.getName(), dealer.getScore(), dealer.getCards());
		for (Player player : players.getPlayers()) {
			outputView.displayAllCardAndScore(player.getName(), player.getScore(), player.getCards());
		}
		Result result = new Result();
		Map<Player, ResultType> gameResult = result.getResult(players.getPlayers(), dealer);
		outputView.displayResult(gameResult);
	}

	private void progressDealerTurn(Dealer dealer, Deck deck) {
		while (dealer.isHit() && !dealer.isBurst()) {
			outputView.displayDealerUnderSevenTeen();
			dealer.addCard(deck.distributeCard());
		}
	}

	private void progressPlayerTurn(Players players, Deck deck) {
		for (Player player : players.getPlayers()) {
			progressOnePlayer(deck, player);
		}
	}

	private void progressOnePlayer(Deck deck, Player player) {
		while (!player.isBurst() && decideHitOrStay(player)) {
			player.addCard(deck.distributeCard());
			outputView.displayAllCard(player.getName(), player.getCards());
		}
	}

	private boolean decideHitOrStay(Player player) {
		String decision = inputDecision(player);
		if (decision.equals("N") || decision.equals("n")) {
			return false;
		}
		return true;
	}

	private String inputDecision(Player player) {
		try {
			String decision = inputView.inputYesOrNo(player.getName());
			return decision;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputDecision(player);
		}
	}

	private void initializeCard(Players players, Dealer dealer, Deck deck) {
		dealer.addTwoCards(deck);
		players.addCardToAllPlayers(deck);
		outputView.displayFirstDistribution(players.getPlayers());
		outputView.displayOneCard(dealer.getCards().get(0));
		for (Player player : players.getPlayers()){
			outputView.displayAllCard(player.getName(), player.getCards());
		}
	}

}
