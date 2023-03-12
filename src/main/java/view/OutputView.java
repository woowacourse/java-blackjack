package view;

import DTO.*;
import domain.player.Participant;

import java.util.List;

public final class OutputView {

    private static final String DELIMITER = ", ";

    public void printSetupGame(final List<String> names) {
        final String participants = String.join(DELIMITER, names);
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", participants);
    }

    public void printDrawCards(final DealerDTO dealerDTO, final List<ParticipantDTO> participantDTOS) {
        System.out.printf("딜러: %s%n", dealerDTO.getDealerCard());
        participantDTOS.forEach(
                participantDTO -> System.out.printf(
                        "%s 카드: %s%n", participantDTO.getParticipantName(), participantDTO.getParticipantCards())
        );
    }

    public void printPlayerCard(final Participant participant) {
        final ParticipantDTO participantDTO = DTOFactory.createParticipantDTO(participant);
        System.out.printf(
                "%s 카드: %s%n", participantDTO.getParticipantName(), participantDTO.getParticipantCards());
    }

    public void printHitOrStay(final boolean isHit) {
        System.out.println(hitOrStay(isHit));
    }


    public void printGameResult(final DealerResultDto dealerResultDto, final List<FinalResultDTO> finalResultDTOs) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d%n", dealerResultDto.getProfit());

        finalResultDTOs.forEach(finalResultDTO -> System.out.printf("%s: %d%n", finalResultDTO.getName(), finalResultDTO.getProfit()));
    }

    private String hitOrStay(final boolean isHit) {
        if (isHit) {
            return "딜러의 총점은 16 이하라 한장의 카드를 더 받았습니다.";
        }
        return "딜러의 총점은 17 이상입니다. 게임을 종료합니다." + System.lineSeparator();
    }
}
