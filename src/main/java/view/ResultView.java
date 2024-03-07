package view;

import static domain.BlackJackGame.INITIAL_CARD_COUNT;

import java.util.List;
import java.util.StringJoiner;

import domain.BlackJackGame;
import view.dto.DealerDto;
import view.dto.ParticipantDto;
import view.dto.PlayerDto;
import view.dto.PlayersDto;

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
        System.out.printf("%n%s: %s", participantDto.name(), parseCards(participantDto.cards()));
    }

    private String parseCards(final List<String> cards) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (String card : cards) {
            stringJoiner.add(card);
        }
        return stringJoiner.toString();
    }

    public void printDealerCardMessage(final DealerDto dealerDto) {
        System.out.printf("%n%s는 %s이하라 한장의 카드를 더 받습니다.%n", dealerDto.name(), BlackJackGame.DEALER_THRESHOLD);
    }

    public void printResult(final List<ParticipantDto> participantDtos){
        participantDtos.forEach(this::printCardAndSum);
        System.out.println();

    }
    private void printCardAndSum(final ParticipantDto participantDto){
        System.out.printf("%n%s: %s - 결과: %d", participantDto.name(), parseCards(participantDto.cards()), participantDto.cardSum());
    }
}
