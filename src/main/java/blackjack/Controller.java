package blackjack;

import blackjack.domain.BlackJack;
import blackjack.domain.participant.Participant;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class Controller {

	public void run() {
		BlackJack blackJack = generateGame();
		startGame(blackJack);
		decidePlayersReceivingAdditionalCard(blackJack);
		decideDealerReceivingAdditionalCard(blackJack);
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

	private void decidePlayersReceivingAdditionalCard(BlackJack blackJack) {
		for (Participant player : blackJack.getPlayers()) {
			decidePlayerReceivingAdditionalCard(blackJack, player);
		}
	}

	private void decidePlayerReceivingAdditionalCard(BlackJack blackJack, Participant player) {

		while (needAdditionalCard(player)) {
			blackJack.handOutCardTo(player);
			ResultView.showEachPlayerStatus(player);
		}
		if (!player.isOverMaxScore()) {
			ResultView.showEachPlayerStatus(player);
		}
	}

	private boolean needAdditionalCard(Participant player) {
		String name = ParticipantDto.from(player).getName();
		return !(player.isOverMaxScore()) && InputView.askAdditionalCard(name);
	}

	private void decideDealerReceivingAdditionalCard(BlackJack blackJack) {
		boolean isDealerEnough = blackJack.getDealer().shouldHit();
		if (!isDealerEnough) {
			blackJack.handOutCardTo(blackJack.getDealer());
		}
		ResultView.showWhetherDealerReceivedOrNot(isDealerEnough);
	}

	private void finishGame(BlackJack blackJack) {
		ResultView.showFinalStatus(blackJack);
		blackJack.calculateResult();
		ResultView.showResult(blackJack);
	}
}
