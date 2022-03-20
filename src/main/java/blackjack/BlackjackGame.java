package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Blackjack;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class BlackjackGame {

	public void play() {
		Blackjack blackJack = generateGame();
		startGame(blackJack);
		decidePlayerHitOrNot(blackJack);
		decideDealerHitOrNot(blackJack);
		finishGame(blackJack);
	}

	private Blackjack generateGame() {
		List<Player> players = InputView.askPlayerNames().stream()
			.map(name -> new Player(name, InputView.askBetAmounts(name)))
			.collect(Collectors.toList());

		return Blackjack.from(players);
	}

	private void startGame(Blackjack blackJack) {
		blackJack.handOutStartingCards();
		ResultView.showStartingStatus(blackJack);
	}

	private void decidePlayerHitOrNot(Blackjack blackJack) {
		for (Player player : blackJack.getPlayers()) {
			decideHitOrNot(blackJack, player);
		}
	}

	private void decideHitOrNot(Blackjack blackJack, Player player) {
		while (shouldHit(player)) {
			blackJack.handOutCardTo(player);
			ResultView.showEachPlayerStatus(player);
		}
		if (!player.isBust()) {
			ResultView.showEachPlayerStatus(player);
		}
	}

	private boolean shouldHit(Player player) {
		return !(player.isBust()) && InputView.askHit(player.getName());
	}

	private void decideDealerHitOrNot(Blackjack blackJack) {
		boolean isDealerEnough = blackJack.getDealer().shouldHit();
		if (!isDealerEnough) {
			blackJack.handOutCardTo(blackJack.getDealer());
		}
		ResultView.showDealerHitOrNot(isDealerEnough);
	}

	private void finishGame(Blackjack blackJack) {
		ResultView.showFinalStatus(blackJack);
		ResultView.showResult(blackJack);
	}
}
