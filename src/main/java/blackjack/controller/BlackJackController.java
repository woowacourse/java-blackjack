package blackjack.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.CardDistributor;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

	private final CardDistributor cardDistributor;

	public BlackJackController(CardDistributor cardDistributor) {
		this.cardDistributor = cardDistributor;
	}

	public void run() {
		List<Name> names = InputView.inputPlayerNames();
		Dealer dealer = new Dealer();
		Players players = initializePlayers(names);
		startGame(dealer, players.getValue());

		if (!isGameEnd(players, dealer)) {
			playPlayers(players.getValue());
			playDealer(dealer);
		}

		showGameResult(players, dealer);
	}

	private Players initializePlayers(List<Name> names) {
		return new Players(names.stream()
			.map(name -> new Player(name, InputView.inputPlayerMoney(name.getValue())))
			.collect(Collectors.toUnmodifiableList()));
	}

	private void startGame(Dealer dealer, List<Player> players) {
		drawInitialCardsToParticipant(dealer);
		for (Player player : players) {
			drawInitialCardsToParticipant(player);
		}
		OutputView.printInitialCards(players, dealer);
	}

	private boolean isGameEnd(Players players, Dealer dealer) {
		return players.isAllBlackJack() || dealer.isBlackJack();
	}

	private void drawInitialCardsToParticipant(Participant participant) {
		while (!participant.isReady()) {
			participant.draw(cardDistributor.distribute());
		}
	}

	private void playPlayers(List<Player> players) {
		for (Player player : players) {
			playPlayer(player);
		}
	}

	private void playPlayer(Player player) {
		while (!player.isFinished()) {
			decideContinueToPlay(player, InputView.inputWantDraw(player));
			OutputView.printCards(player);
		}
	}

	private void decideContinueToPlay(Player player, boolean wantDraw) {
		if (wantDraw) {
			player.draw(cardDistributor.distribute());
			return;
		}
		player.stay();
	}

	private void playDealer(Dealer dealer) {
		while (!dealer.isFinished()) {
			OutputView.printDealerDrawInfo();
			dealer.draw(cardDistributor.distribute());
		}
	}

	private void showGameResult(Players players, Dealer dealer) {
		GameResult gameResult = new GameResult(players.match(dealer));
		OutputView.printCardsResult(dealer, players);
		OutputView.printGameResult(gameResult);
	}
}
