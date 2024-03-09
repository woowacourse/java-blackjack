package blackjack.controller;

import blackjack.view.object.Command;
import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GameResult;
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
		players.initAllPlayersCard(deck);
		dealer.initCard(deck);
		printInitialHands(players, dealer);
	}

	private void printInitialHands(Players players, Dealer dealer) {
		DealerInitialHandDto dealerInitialHandDto = dealer.convertDealerToInitialHandDto();
		List<GamerHandDto> playersInitialHandDto = players.convertPlayersToDto();

		outputView.printInitialHands(dealerInitialHandDto, playersInitialHandDto);
	}

	private void distributeCardToPlayers(Players players, Deck deck) {
		for (Player player : players.getPlayers()) {
			distributeCardToPlayer(deck, player);
		}
	}

	private void distributeCardToPlayer(Deck deck, Player player) {
		while (canDistribute(player)) {
			player.addCard(deck.draw());
			outputView.printPlayerHand(player.convertGamerToDto());
		}
	}

	private boolean canDistribute(Player player) {
		return player.canReceiveCard() && Command.isHit(getCommand(player));
	}

	private Command getCommand(Player player) {
		return inputView.receiveCommand(player.getName().value());
	}

	private void distributeCardToDealer(Dealer dealer, Deck deck) {
		while (dealer.canReceiveCard()) {
			dealer.addCard(deck.draw());
			outputView.printDealerMessage(dealer.getName().value());
		}
	}

	private void printAllGamerScores(Dealer dealer, Players players) {
		outputView.printEmptyLine();
		outputView.printScore(dealer.convertGamerToDto(), dealer.getScore());
		printPlayersScores(players);
	}

	private void printPlayersScores(Players players) {
		for (Player player : players.getPlayers()) {
			outputView.printScore(player.convertGamerToDto(), player.getScore());
		}
	}

	private void printResult(Dealer dealer, Players players) {
		PlayerResultsDto playerResultsDto = players.convertPlayersToResultDto(dealer.getScore());
		List<GameResult> playerResults = new ArrayList<>(playerResultsDto.resultMap().values());
		DealerResultDto dealerResultDto = dealer.convertDealerToResultDto(playerResults);

		outputView.printResult(dealerResultDto, playerResultsDto);
	}
}
