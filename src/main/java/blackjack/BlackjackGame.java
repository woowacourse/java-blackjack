package blackjack;

import blackjack.domain.BlackJack;
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
		try {
			return BlackJack.from(InputView.askPlayerName());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return generateGame();
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
		if (!player.bust()) {
			ResultView.showEachPlayerStatus(player);
		}
	}

	private boolean shouldHit(Player player) {
		String name = ParticipantDto.from(player).getName();
		return !(player.bust()) && InputView.askHit(name);
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
