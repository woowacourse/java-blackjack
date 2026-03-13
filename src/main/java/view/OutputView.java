package view;

import dto.CardInfoDto;
import dto.ParticipantCardsDto;
import dto.ParticipantGameResultDto;
import java.util.List;

public class OutputView {

    public static void printGameInitialMessage(List<String> playersNames) {
        String playersNamesMessage = String.join(",", playersNames);
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
        System.out.println("## 최종 승패");

        for (ParticipantGameResultDto participantGameResultDto : participantGameResultDtos) {
            if (participantGameResultDto.name().equals("딜러")) {
                System.out.println(toDealerGameResultString(participantGameResultDto));
                continue;
            }

            System.out.println(toPlayerGameResultString(participantGameResultDto));
        }
    }

    private static String toCardsInfoString(ParticipantCardsDto participantCardsDto) {
        List<String> cardsInfo = participantCardsDto.cardsInfo().stream()
                .map(cardInfoDto -> cardInfoDto.shapeKoreanName() + cardInfoDto.number()).toList();

        String cardsInfoString = String.join(",", cardsInfo);
        return cardsInfoString;
    }

    private static String toDealerGameResultString(ParticipantGameResultDto participantGameResultDto) {
        return String.format("%s: %d승 %d패 %d무", participantGameResultDto.name(), participantGameResultDto.win(),
                participantGameResultDto.lose(), participantGameResultDto.draw());
    }

    private static String toPlayerGameResultString(ParticipantGameResultDto dto) {
        StringBuilder sb = new StringBuilder(dto.name() + ": ");
        if (dto.win() > 0) {
            sb.append(dto.win()).append("승 ");
        }
        if (dto.lose() > 0) {
            sb.append(dto.lose()).append("패 ");
        }
        if (dto.draw() > 0) {
            sb.append(dto.draw()).append("무");
        }
        return sb.toString().trim();
    }

    public static void printInitialDealerCards(ParticipantCardsDto participantCardsDto) {
        CardInfoDto dealerCard = participantCardsDto.cardsInfo().getFirst();
        String cardInfoString = dealerCard.shapeKoreanName() + dealerCard.number();
        System.out.printf("%s: %s%n", participantCardsDto.name(), cardInfoString);
    }
}
