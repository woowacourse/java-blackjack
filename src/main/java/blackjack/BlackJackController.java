package blackjack;

import blackjack.domain.BlackJack;
import blackjack.domain.Participant;
import blackjack.domain.dto.BlackJackDto;
import blackjack.domain.dto.ParticipantDto;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class BlackJackController {

	public void run() {
		BlackJack blackJack = BlackJack.createFrom(InputView.askPlayerNameInput());
		BlackJackDto blackJackDto = BlackJackDto.from(blackJack);

		startGame(blackJack, blackJackDto);
		decidePlayersReceivingAdditionalCard(blackJack);
		decideDealerReceivingAdditionalCard(blackJack, blackJackDto);
		finishGame(blackJack, blackJackDto);
		InputView.terminate();
	}

	private void startGame(BlackJack blackJack, BlackJackDto blackJackDto) {
		blackJack.handOutStartingCards();
		ResultView.showStartingStatus(blackJackDto);
	}

	private void decidePlayersReceivingAdditionalCard(BlackJack blackJack) {
		BlackJackDto blackJackDto = BlackJackDto.from(blackJack);
		for (Participant player : blackJackDto.getPlayers()) {
			decidePlayerReceivingAdditionalCard(blackJack, player);
		}
	}

	private void decidePlayerReceivingAdditionalCard(BlackJack blackJack, Participant player) {
		while (InputView.askAdditionalCardInput(ParticipantDto.from(player).getName()) || !player.isOverMaxScore()) {
			blackJack.handOutCardTo(player);
			ResultView.showEachPlayerCurrentStatus(player);
		}
		ResultView.showEachPlayerCurrentStatus(player);
	}

	private void decideDealerReceivingAdditionalCard(BlackJack blackJack, BlackJackDto blackJackDto) {
		boolean dealerNeedAdditionalCard = blackJack.isDealerNeedAdditionalCard();
		if (dealerNeedAdditionalCard) {
			blackJack.handOutCardTo(blackJackDto.getDealer());
		}
		ResultView.showWhetherDealerReceivedOrNot(dealerNeedAdditionalCard);
	}

	private void finishGame(BlackJack blackJack, BlackJackDto blackJackDto) {
		ResultView.showFinalStatus(blackJackDto);
		blackJack.calculateResult();
		ResultView.showResult(blackJackDto);
	}
}
