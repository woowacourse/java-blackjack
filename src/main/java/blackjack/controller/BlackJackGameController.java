package blackjack.controller;

import blackjack.domain.betting.BettingToken;
import blackjack.domain.betting.BettingTokens;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.process.BlackJackGame;
import blackjack.domain.process.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.stream.Collectors;

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
		BlackJackGame blackJackGame = new BlackJackGame(new Deck());
		BettingTokens bettingTokens = generateBettingMoneys(players);
		initializeCard(players, dealer, blackJackGame);
		progressPlayerTurn(players, blackJackGame);
		progressDealerTurn(players, dealer, blackJackGame);
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
		outputView.displayNewLine();
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
		Result result = Result.of(players, dealer, bettingTokens);
		outputView.displayNewLine();
		outputView.displayResult(dealer, result);
	}

	private void progressDealerTurn(Players players, Dealer dealer, BlackJackGame blackJackGame) {
		outputView.displayNewLine();
		while (dealer.canHit() && !players.isAllPlayersBlackJackOrBust()) {
			outputView.displayDealerUnderSevenTeen();
			blackJackGame.drawTo(dealer);
		}
	}

	private void progressPlayerTurn(Players players, BlackJackGame blackJackGame) {
		players.getPlayers().forEach(player -> progressOnePlayer(player, blackJackGame));
	}

	private void progressOnePlayer(Player player, BlackJackGame blackJackGame) {
		while (player.canHit() && inputDecision(player)) {
			blackJackGame.drawTo(player);
			outputView.displayAllCard(player);
		}
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

	private void initializeCard(Players players, Dealer dealer, BlackJackGame blackJackGame) {
		blackJackGame.shuffleDeck();
		blackJackGame.initDrawTo(dealer);
		outputView.displayNewLine();
		outputView.displayFirstDistribution(players, dealer);
		outputView.displayDealerOneCard(dealer);
		for (Player player : players.getPlayers()) {
			blackJackGame.initDrawTo(player);
			outputView.displayAllCard(player);
		}
		outputView.displayNewLine();
	}
}
