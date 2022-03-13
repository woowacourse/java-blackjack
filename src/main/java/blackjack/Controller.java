package blackjack;

import blackjack.domain.BlackJack;
import blackjack.domain.Participant;
import blackjack.domain.dto.BlackJackDto;
import blackjack.domain.dto.ParticipantDto;
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
			return BlackJack.createFrom(InputView.askPlayerName());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return generateGame();
		}
	}

	private void startGame(BlackJack blackJack) {
		blackJack.handOutStartingCards();
		ResultView.showStartingStatus(BlackJackDto.from(blackJack));
	}

	private void decidePlayersReceivingAdditionalCard(BlackJack blackJack) {
		BlackJackDto blackJackDto = BlackJackDto.from(blackJack);
		for (Participant player : blackJackDto.getPlayers()) {
			decidePlayerReceivingAdditionalCard(blackJack, player);
		}
	}

	private void decidePlayerReceivingAdditionalCard(BlackJack blackJack, Participant player) {
		while (!(player.isOverMaxScore()) && InputView.askAdditionalCard(ParticipantDto.from(player).getName())) {
			blackJack.handOutCardTo(player);
			ResultView.showEachPlayerStatus(player);
		}
		if (!player.isOverMaxScore()) {
			ResultView.showEachPlayerStatus(player);
		}
	}

	private void decideDealerReceivingAdditionalCard(BlackJack blackJack) {
		boolean isDealerEnough = blackJack.isDealerEnough();
		if (!isDealerEnough) {
			blackJack.handOutCardTo(BlackJackDto.from(blackJack).getDealer());
		}
		ResultView.showWhetherDealerReceivedOrNot(isDealerEnough);
	}

	private void finishGame(BlackJack blackJack) {
		BlackJackDto blackJackDto = BlackJackDto.from(blackJack);
		ResultView.showFinalStatus(blackJackDto);
		blackJack.calculateResult();
		ResultView.showResult(blackJackDto);
	}
}
