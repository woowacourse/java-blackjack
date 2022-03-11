package blackjack.view;

import java.util.List;

import blackjack.domain.participant.Participant;
import blackjack.dto.MatchResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.view.input.InputView;
import blackjack.view.output.OutputView;


public class BlackjackView {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public List<String> requestPlayerNames() {
        outputView.printMessageOfRequestPlayerNames();
        return inputView.requestPlayerNames();
    }

    public void printFirstDistributedCards(final ParticipantDto dealderDto, final List<ParticipantDto> playerDtos) {
        outputView.printDistributedCards(dealderDto);
        playerDtos.forEach(outputView::printDistributedCards);
    }

    public boolean requestContinuable(final String playerName) {
        outputView.printMessageOfRequestContinuable(playerName);
        return inputView.requestContinuable();
    }

    public void printDistributedCards(final ParticipantDto participantDto) {
        outputView.printDistributedCards(participantDto);
    }

    public void printMessageOfDealerDrawCard() {
        outputView.printMessageOfDealerDrawCard();
    }

    public void printMatchResult(final List<ParticipantDto> participantDtos, final MatchResultDto matchResultDto) {
        outputView.printScores(participantDtos);
        outputView.printMatchResult(matchResultDto);
    }
}
