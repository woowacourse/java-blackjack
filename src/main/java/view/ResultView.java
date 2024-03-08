package view;

import static domain.BlackjackGame.DEALER_THRESHOLD;
import static domain.BlackjackGame.INITIAL_CARD_COUNT;

import java.util.List;

import view.dto.GameResultDto;
import view.dto.card.CardsDto;
import view.dto.participant.DealerDto;
import view.dto.participant.DealerResultDto;
import view.dto.participant.ParticipantDto;
import view.dto.participant.PlayerResultsDto;
import view.dto.participant.PlayersDto;

public class ResultView {

    public void printInitialCards(final DealerDto dealerDto, final PlayersDto playersDto) {
        printInitialDealMessage(dealerDto, playersDto);
        printParticipantHand(dealerDto);
        printPlayerHands(playersDto);
        System.out.println();
    }

    private void printInitialDealMessage(final DealerDto dealerDto, final PlayersDto playersDto) {
        System.out.printf("%n%s와 %s에게 %d장을 나누었습니다.",
                dealerDto.name(),
                parsePlayerNames(playersDto),
                INITIAL_CARD_COUNT
        );
    }

    private String parsePlayerNames(final PlayersDto playersDto) {
        return playersDto.players()
                .stream()
                .map(ParticipantDto::name)
                .reduce((player1, player2) -> player1 + ", " + player2)
                .orElse("");
    }

    public void printPlayerHands(final PlayersDto playersDto) {
        playersDto.players().forEach(this::printParticipantHand);
    }

    public void printParticipantHand(final ParticipantDto participantDto) {
        CardsDto cards = participantDto.getCards();
        System.out.printf("%n%s: %s",
                participantDto.name(),
                cards.parseCards()
        );
    }

    public void printDealerCardMessage(final DealerDto dealerDto) {
        System.out.printf("%n%s는 %s이하라 한장의 카드를 더 받습니다.%n%n",
                dealerDto.name(),
                DEALER_THRESHOLD
        );
    }

    public void printResults(final List<ParticipantDto> participantDtos, final GameResultDto gameResultDto) {
        System.out.println();
        participantDtos.forEach(this::printCardAndSum);
        System.out.println();
        printGameResults(gameResultDto);
        System.out.println();
    }

    private void printCardAndSum(final ParticipantDto participantDto) {
        CardsDto cards = participantDto.getCards();
        System.out.printf("%s: %s - 결과: %d%n",
                participantDto.name(),
                cards.parseCards(),
                participantDto.score()
        );
    }

    private void printGameResults(final GameResultDto gameResultDto) {
        DealerResultDto dealerResult = gameResultDto.getDealerResult();
        PlayerResultsDto playerResults = gameResultDto.getPlayersResult();
        System.out.println("## 최종 승패");
        System.out.println(dealerResult.parseResult());
        System.out.print(playerResults.parseResult());
    }
}
