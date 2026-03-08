package blackjack.view;

import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantInitialDealDtos;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.PlayerResultDto;
import java.util.List;

public class BlackjackView {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public String readPlayers() {
        return inputView.readPlayerNames();
    }

    public String askHit(final String playerName) {
        return inputView.askHit(playerName);
    }

    public void printInitialDeal(ParticipantInitialDealDtos participantInitialDealDtos) {
        outputView.printInitialDeal(participantInitialDealDtos);
    }

    public void printPlayerCards(ParticipantCardsDto participantCardsDto) {
        outputView.printPlayerCards(participantCardsDto);
    }

    public void printDealerHit() {
        outputView.printDealerHit();
    }

    public void printScore(List<ParticipantScoreDto> participantScoreDtos) {
        outputView.printScore(participantScoreDtos);
    }

    public void printResult(List<PlayerResultDto> playerResultDtos) {
        outputView.printResult(playerResultDtos);
    }
}
