package blackjack.controller;

import blackjack.domain.BettingToken;
import blackjack.domain.BettingTokens;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.stream.Collectors;

public class BlackJackGameController {
	private static final int INIT_DISTRIBUTE_AMOUNT = 2;
	private static final int EACH_TURN_DISTRIBUTE_AMOUNT = 1;
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
		BettingTokens bettingTokens = generateBettingMoneys(players);
		initializeCard(players, dealer, deck);
		progressPlayerTurn(players, deck);
		progressDealerTurn(players, dealer, deck);
		makeResult(players, dealer, bettingTokens);
	}

	private Players generatePlayers() {
		try {
			return Players.from(inputView.inputPlayerNames().stream().map(Name::new).collect(Collectors.toList()));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return generatePlayers();
		}
	}

	private BettingTokens generateBettingMoneys(Players players) {
		BettingTokens bettingTokens = new BettingTokens(players.getPlayersSize());
		for (Player player : players.getPlayers()) {
			bettingTokens.addBettingMoney(inputMoney(player.getName()));
		}
		return bettingTokens;
	}

	private void makeResult(Players players, Dealer dealer, BettingTokens bettingTokens) {
		outputView.displayNewLine();
		outputView.displayAllCardAndScore(dealer);
		for (Player player : players.getPlayers()) {
			outputView.displayAllCardAndScore(player);
		}
		Result result = new Result(players, bettingTokens);
		outputView.displayNewLine();
		outputView.displayResult(dealer, result);
	}

	private void progressDealerTurn(Players players, Dealer dealer, Deck deck) {
		outputView.displayNewLine();
		while (dealer.canHit() && !players.isAllPlayersBlackJackOrBust()) {
			outputView.displayDealerUnderSevenTeen();
			dealer.addCards(deck.distributeCards(EACH_TURN_DISTRIBUTE_AMOUNT));
		}
	}

	private void progressPlayerTurn(Players players, Deck deck) {
		players.getPlayers().forEach(player -> progressOnePlayer(player, deck));
	}

	private void progressOnePlayer(Player player, Deck deck) {
		while (player.canHit() && decideHitOrStay(player)) {
			player.addCards(deck.distributeCards(EACH_TURN_DISTRIBUTE_AMOUNT));
			outputView.displayAllCard(player);
		}
	}

	private boolean decideHitOrStay(Player player) {
		return inputDecision(player);
	}

	private boolean inputDecision(Player player) {
		try {
			return inputView.inputYesOrNo(player.getName());
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputDecision(player);
		}
	}

	private BettingToken inputMoney(String name) {
		try {
			return new BettingToken(inputView.inputMoney(name));
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputMoney(name);
		}
	}

	private void initializeCard(Players players, Dealer dealer, Deck deck) {
		deck.shuffle();
		dealer.addCards(deck.distributeCards(INIT_DISTRIBUTE_AMOUNT));
		outputView.displayFirstDistribution(players, dealer);
		outputView.displayDealerOneCard(dealer);
		for (Player player : players.getPlayers()) {
			player.addCards(deck.distributeCards(INIT_DISTRIBUTE_AMOUNT));
			outputView.displayAllCard(player);
		}
		outputView.displayNewLine();
	}
}
