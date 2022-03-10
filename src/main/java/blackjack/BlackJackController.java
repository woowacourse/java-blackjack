package blackjack;

import blackjack.domain.BlackJack;
import blackjack.domain.dto.BlackJackDto;
import blackjack.domain.Participant;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class BlackJackController {

	private static final int MAX_SCORE = 21;

	public void run() {
		BlackJack blackJack = BlackJack.createFrom(InputView.askPlayerNameInput());
		BlackJackDto blackJackDto = BlackJackDto.from(blackJack);

		startGame(blackJack, blackJackDto);
		decidePlayersReceivingAdditionalCard(blackJack, blackJackDto);
		decideDealerReceivingAdditionalCard(blackJack, blackJackDto);
		finishGame(blackJack, blackJackDto);
	}

	private void startGame(BlackJack blackJack, BlackJackDto blackJackDto) {
		blackJack.handOutStartingCards();
		ResultView.showStartingStatus(blackJackDto);
	}

	private void decidePlayersReceivingAdditionalCard(BlackJack blackJack, BlackJackDto blackJackDto) {
		for (Participant player : blackJack.getPlayers()) {
			decidePlayerReceivingAdditionalCard(blackJack, blackJackDto, player);
		}
	}

	private void decidePlayerReceivingAdditionalCard(BlackJack blackJack, BlackJackDto blackJackDto, Participant player) {
		while (InputView.askAdditionalCardInput(player.getName()) || player.getScore() < MAX_SCORE) {
			blackJack.handOutCardTo(player);
			ResultView.showEachPlayerCurrentStatus(blackJackDto, player);
		}
		ResultView.showEachPlayerCurrentStatus(blackJackDto, player);
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
