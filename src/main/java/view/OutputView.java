package view;

import dto.CardInfoDto;
import dto.ParticipantCardsDto;
import dto.ParticipantGameResultDto;
import java.util.List;

public class OutputView {

    public static void printGameInitialMessage(List<String> playersNames) {
        String playersNamesMessage = String.join(", ", playersNames);
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", playersNamesMessage);
    }

    public static void printCards(ParticipantCardsDto participantCardsDto) {
        System.out.printf("%s: %s%n", participantCardsDto.name(), toCardsInfoString(participantCardsDto));
    }

    public static void printFinalCards(ParticipantCardsDto participantCardsDto) {
        System.out.println(participantCardsDto.name() + ": " + toCardsInfoString(participantCardsDto) + " - 결과: "
                + participantCardsDto.totalScore());
    }

    public static void printDealerMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printGameResult(List<ParticipantGameResultDto> participantGameResultDtos) {
        System.out.println();
        System.out.println("## 최종 수익");
        for (ParticipantGameResultDto participantGameResultDto : participantGameResultDtos) {
            System.out.println(toParticipantGameResultString(participantGameResultDto));
        }
    }

    private static String toCardsInfoString(ParticipantCardsDto participantCardsDto) {
        List<String> cardsInfo = participantCardsDto.cardsInfo().stream()
                .map(cardInfoDto -> cardInfoDto.numberDisplayName() + cardInfoDto.shape()).toList();

        String cardsInfoString = String.join(", ", cardsInfo);
        return cardsInfoString;
    }

    private static String toParticipantGameResultString(ParticipantGameResultDto participantGameResultDto) {
        return String.format("%s: %d", participantGameResultDto.name(), participantGameResultDto.profit());
    }

    public static void printInitialDealerCards(ParticipantCardsDto participantCardsDto) {
        CardInfoDto dealerCard = participantCardsDto.cardsInfo().getFirst();
        String cardInfoString = dealerCard.numberDisplayName() + dealerCard.shape();
        System.out.printf("%s: %s%n", participantCardsDto.name(), cardInfoString);
    }
}
