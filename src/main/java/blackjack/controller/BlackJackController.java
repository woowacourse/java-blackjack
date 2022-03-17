package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.Result;
import blackjack.domain.dto.BlackJackDto;
import blackjack.domain.participant.Participant;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class BlackJackController {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackController() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    public void run() {
        BlackJack blackJack = BlackJack.createFrom(inputView.askPlayerNameInput());
        BlackJackDto blackJackDto = BlackJackDto.from(blackJack);

        startGame(blackJack, blackJackDto);
        betMoney(blackJackDto);
        decidePlayersReceivingAdditionalCard(blackJack, blackJackDto);
        decideDealerReceivingAdditionalCard(blackJack, blackJackDto);
        finishGame(blackJackDto);

        inputView.terminate();
    }

    private void startGame(BlackJack blackJack, BlackJackDto blackJackDto) {
        blackJack.handOutStartingCards();
        resultView.showStartingStatus(blackJackDto);
    }

    private void betMoney(BlackJackDto blackJackDto) {
        for (Participant player : blackJackDto.getParticipants().getPlayers()) {
            player.bet(inputView.askBettingAmount(player.getName()));
        }
    }

    private void decidePlayersReceivingAdditionalCard(BlackJack blackJack, BlackJackDto blackJackDto) {
        for (Participant player : blackJackDto.getParticipants().getPlayers()) {
            decidePlayerReceivingAdditionalCard(blackJack, blackJackDto, player);
        }
    }

    private void decidePlayerReceivingAdditionalCard(BlackJack blackJack, BlackJackDto blackJackDto,
                                                     Participant player) {
        resultView.showEachPlayerCurrentStatus(blackJackDto, player);
        while (!player.isOverMaxScore() && inputView.askAdditionalCardInput(player.getName())) {
            blackJack.handOutCardTo(player);
            resultView.showEachPlayerCurrentStatus(blackJackDto, player);
        }
    }

    private void decideDealerReceivingAdditionalCard(BlackJack blackJack, BlackJackDto blackJackDto) {
        boolean dealerNeedAdditionalCard = blackJackDto.getParticipants().isDealerNeedAdditionalCard();
        if (dealerNeedAdditionalCard) {
            blackJack.handOutCardTo(blackJackDto.getParticipants().getDealer());
        }
        resultView.showWhetherDealerReceivedOrNot(dealerNeedAdditionalCard);
    }

    private void finishGame(BlackJackDto blackJackDto) {
        resultView.showFinalStatus(blackJackDto);
        Result result = new Result(blackJackDto.getParticipants());
        result.calculatePlayersRevenue();
        resultView.showResult(blackJackDto, result);
    }
}
