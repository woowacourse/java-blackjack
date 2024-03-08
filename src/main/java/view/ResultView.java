package view;

import static domain.BlackjackGame.INITIAL_CARD_COUNT;

import java.util.List;
import java.util.StringJoiner;

import domain.BlackjackGame;
import view.dto.GameResultDto;
import view.dto.participant.DealerDto;
import view.dto.participant.ParticipantDto;
import view.dto.participant.PlayerDto;
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
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (PlayerDto playerDto : playersDto.dtos()) {
            stringJoiner.add(playerDto.name());
        }
        return stringJoiner.toString();
    }

    public void printPlayerHands(final PlayersDto playersDto) {
        List<PlayerDto> playerDtos = playersDto.dtos();
        for (PlayerDto playerDto : playerDtos) {
            printParticipantHand(playerDto);
        }
    }

    public void printParticipantHand(final ParticipantDto participantDto) {
        System.out.printf("%n%s: %s", participantDto.name(), participantDto.getCards()
                .parseCards());
    }

    public void printDealerCardMessage(final DealerDto dealerDto) {
        System.out.printf("%n%s는 %s이하라 한장의 카드를 더 받습니다.%n%n", dealerDto.name(), BlackjackGame.DEALER_THRESHOLD);
    }

    public void printResult(final List<ParticipantDto> participantDtos,
                            final GameResultDto gameResultDto) {
        System.out.println();
        participantDtos.forEach(this::printCardAndSum);
        System.out.println();
        printGameResults(gameResultDto);
        System.out.println();
    }

    private void printCardAndSum(final ParticipantDto participantDto) {
        System.out.printf("%s: %s - 결과: %d%n", participantDto.name(), participantDto.getCards()
                        .parseCards(),
                participantDto.score());
    }

    private void printGameResults(final GameResultDto gameResultDto) {
        System.out.println("## 최종 승패");
        System.out.println(gameResultDto.getDealerResult()
                .parseResult());
        System.out.print(gameResultDto.getPlayersResult()
                .parseResult());
    }
}
