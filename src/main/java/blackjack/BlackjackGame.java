package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.BlackJack;
import blackjack.domain.Money;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class BlackjackGame {

	public void play() {
		BlackJack blackJack = generateGame();
		startGame(blackJack);
		decidePlayerHitOrNot(blackJack);
		decideDealerHitOrNot(blackJack);
		finishGame(blackJack);
	}

	private BlackJack generateGame() {
		List<Name> playerNames = askPlayerName();
		List<Player> players = new ArrayList<>();
		for (Name playerName : playerNames) {
			Money money = askBetAmount(playerName);
			players.add(new Player(playerName, money));
		}

		return BlackJack.from(players);
	}

	private Money askBetAmount(Name playerName) {
		String name = playerName.getName();
		try {
			return new Money(InputView.askBetAmount(name));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return askBetAmount(playerName);
		}
	}

	private List<Name> askPlayerName() {
		List<String> inputs = InputView.askPlayerName();
		try {
			return inputs.stream()
				.map(Name::from)
				.collect(Collectors.toList());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return askPlayerName();
		}
	}

	private void startGame(BlackJack blackJack) {
		blackJack.handOutStartingCards();
		ResultView.showStartingStatus(blackJack);
	}

	private void decidePlayerHitOrNot(BlackJack blackJack) {
		for (Player player : blackJack.getPlayers()) {
			decideHitOrNot(blackJack, player);
		}
	}

	private void decideHitOrNot(BlackJack blackJack, Player player) {

		while (shouldHit(player)) {
			blackJack.handOutCardTo(player);
			ResultView.showEachPlayerStatus(player);
		}
		if (!player.isBust()) {
			ResultView.showEachPlayerStatus(player);
		}
	}

	private boolean shouldHit(Player player) {
		return !(player.isBust()) && player.shouldHit(InputView.askHit(player.getName()));
	}

	private void decideDealerHitOrNot(BlackJack blackJack) {
		boolean isDealerEnough = blackJack.getDealer().shouldHit();
		if (!isDealerEnough) {
			blackJack.handOutCardTo(blackJack.getDealer());
		}
		ResultView.showDealerHitOrNot(isDealerEnough);
	}

	private void finishGame(BlackJack blackJack) {
		ResultView.showFinalStatus(blackJack);
		blackJack.calculateResult();
		ResultView.showResult(blackJack);
	}
}
