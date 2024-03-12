package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.dto.DealerInitialHandDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GamerHandDto;
import blackjack.dto.PlayerResultsDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

	private static final String HIT_COMMAND = "y";
	private static final String STAND_COMMAND = "n";

	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackController() {
		this.inputView = new InputView();
		this.outputView = new OutputView();
	}

	public void run() {
		Players players = getPlayers();
		Dealer dealer = new Dealer();
		Deck deck = new Deck();
		deck.shuffle();
		outputView.printEmptyLine();

		setUpInitialHands(players, deck, dealer);
		distributeCardToPlayers(players, deck);
		distributeCardToDealer(dealer, deck);
		printAllGamerScores(dealer, players);
		printResult(dealer, players);
	}

	private Players getPlayers() {
		List<String> playerNames = inputView.receivePlayerNames();

		return new Players(playerNames);
	}

	private void setUpInitialHands(Players players, Deck deck, Dealer dealer) {
		players.initAllPlayersCard(deck, BlackjackConstants.INITIAL_CARD_COUNT.getValue());
		dealer.initCard(deck, BlackjackConstants.INITIAL_CARD_COUNT.getValue());
		printInitialHands(players, dealer);
	}

	private void printInitialHands(Players players, Dealer dealer) {
		DealerInitialHandDto dealerInitialHandDto = DealerInitialHandDto.fromDealer(dealer);
		List<GamerHandDto> playerInitialHandDto = players.getPlayers().stream()
			.map(GamerHandDto::fromGamer)
			.toList();

		outputView.printInitialHands(dealerInitialHandDto, playerInitialHandDto);
		outputView.printEmptyLine();
	}

	private void distributeCardToPlayers(Players players, Deck deck) {
		for (Player player : players.getPlayers()) {
			distributeCardToPlayer(deck, player);
		}
	}

	private void distributeCardToPlayer(Deck deck, Player player) {
		while (canDistribute(player)) {
			player.addCard(deck.draw());
			outputView.printGamerNameAndHand(GamerHandDto.fromGamer(player));
		}
		outputView.printEmptyLine();
	}

	private boolean canDistribute(Player player) {
		return player.canReceiveCard() && HIT_COMMAND.equals(getCommand(player));
	}

	private String getCommand(Player player) {
		String command = inputView.receiveCommand(player.getName().value());
		if (HIT_COMMAND.equals(command) || STAND_COMMAND.equals(command)) {
			return command;
		}
		throw new IllegalArgumentException(HIT_COMMAND + " 또는 " + STAND_COMMAND + "만 입력 가능합니다.");
	}

	private void distributeCardToDealer(Dealer dealer, Deck deck) {
		while (dealer.canReceiveCard()) {
			dealer.addCard(deck.draw());
			outputView.printDealerMessage(dealer.getName().value());
		}
		outputView.printEmptyLine();
	}

	private void printAllGamerScores(Dealer dealer, Players players) {
		outputView.printScore(GamerHandDto.fromGamer(dealer), dealer.getScore());
		printPlayersScores(players);
		outputView.printEmptyLine();
	}

	private void printPlayersScores(Players players) {
		players.getPlayers().forEach(player -> outputView.printScore(
			GamerHandDto.fromGamer(player), player.getScore()
		));
	}

	private void printResult(Dealer dealer, Players players) {
		PlayerResultsDto playerResultsDto = PlayerResultsDto.ofPlayersAndDealerScore(players, dealer);
		DealerResultDto dealerResultDto = DealerResultDto.ofDealerAndPlayers(dealer, players);

		outputView.printFinalResult(dealerResultDto, playerResultsDto);
	}
}
