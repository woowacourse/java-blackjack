package blackjack.view;

import blackjack.dto.DealerHitDto;
import blackjack.dto.GameResultDtos;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDtos;

public class BlackjackView {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public String readPlayers() {
        outputView.askPlayerNames();
        return inputView.readPlayerNames();
    }

    public long readBetAmount(final String playerName) {
        outputView.askBetAmount(playerName);
        return inputView.readLong();
    }

    public boolean isHitAnswer(final String playerName) {
        outputView.askHit(playerName);
        return inputView.isHitAnswer();
    }

    public void printInitialDeal(InitialDealDtos initialDealDtos) {
        outputView.printInitialDeal(initialDealDtos);
    }

    public void printPlayerCards(ParticipantCardsDto participantCardsDto) {
        outputView.printParticipantCards(participantCardsDto);
    }

    public void printDealerHit(DealerHitDto dealerHitDto) {
        if (dealerHitDto.hitCount() <= 0) {
            return;
        }
        outputView.printDealerHit(dealerHitDto);
    }

    public void printScore(ParticipantScoreDtos participantScoreDtos) {
        outputView.printScore(participantScoreDtos);
    }

    public void printResult(GameResultDtos resultDto) {
        outputView.printResults(resultDto);
    }
}
