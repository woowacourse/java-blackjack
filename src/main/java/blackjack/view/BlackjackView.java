package blackjack.view;

import blackjack.dto.GameResultDto;
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

    public void printDealerHit(final String dealerName) {
        outputView.printDealerHit(dealerName);
    }

    public void printScore(ParticipantScoreDtos participantScoreDtos) {
        outputView.printScore(participantScoreDtos);
    }

    public void printResult(GameResultDto resultDto) {
        outputView.printResults(resultDto);
    }
}
