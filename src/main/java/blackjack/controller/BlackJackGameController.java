package blackjack.controller;

import blackjack.domain.betting.BettingToken;
import blackjack.domain.betting.BettingTokens;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.process.BlackJackGame;
import blackjack.domain.process.BettingResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.print.DocFlavor.READER;

public class BlackJackGameController {
	private final InputView inputView;
	private final OutputView outputView;

	public BlackJackGameController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void gameStart() {
		BlackJackGame blackJackGame = new BlackJackGame(new Deck());
		Players players = generatePlayers(blackJackGame);
		Dealer dealer = new Dealer();
		initializeCard(players, dealer, blackJackGame);
		progressPlayerTurn(players, blackJackGame);
		progressDealerTurn(players, dealer, blackJackGame);
		makeResult(players, dealer);
	}

	private Players generatePlayers(BlackJackGame blackJackGame) {
		List<Name> names = generateNames(blackJackGame);
		return blackJackGame.generatePlayers(names, generateBettingMoneys(names));
	}

	private List<Name> generateNames(BlackJackGame blackJackGame) {
		try {
			return blackJackGame.generateNames(inputView.inputPlayerNames());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return generateNames(blackJackGame);
		}
	}

	private List<BettingToken> generateBettingMoneys(List<Name> names) {
		outputView.displayNewLine();
		List<BettingToken> bettingTokens = new ArrayList<>();
		for (Name name : names) {
			bettingTokens.add(inputMoney(name.getName()));
		}
		return bettingTokens;
	}

	private void makeResult(Players players, Dealer dealer) {
		outputView.displayNewLine();
		outputView.displayAllCardAndScore(dealer);
		for (Player player : players.getPlayers()) {
			outputView.displayAllCardAndScore(player);
		}
		BettingResult bettingResult = BettingResult.of(players, dealer);
		outputView.displayNewLine();
		outputView.displayResult(bettingResult);
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
