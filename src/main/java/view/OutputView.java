package view;

import dto.CardInfoDto;
import dto.ParticipantCardsDto;
import java.util.List;
import java.util.Map;

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

    public static void printGameResult(Map<String, Boolean> gameResult) {
        StringBuilder resultMessage = new StringBuilder();
        int winCount = 0;
        int loseCount = 0;

        for (Map.Entry<String, Boolean> entry : gameResult.entrySet()) {
            if (entry.getValue().equals(true)) {
                resultMessage.append(entry.getKey()).append(": ").append("승\n");
                winCount += 1;
                continue;
            }

            resultMessage.append(entry.getKey()).append(": ").append("패\n");
            loseCount += 1;
        }

        System.out.println("## 최종 승패");
        System.out.println("딜러: " + loseCount + "승" + " " + winCount + "패");
        System.out.printf(resultMessage.toString());
    }

    public static void printInitialPlayerCards(ParticipantCardsDto participantCardsDto) {
        System.out.printf("%s: %s%n", participantCardsDto.name(), toCardsInfoString(participantCardsDto));
    }

    private static String toCardsInfoString(ParticipantCardsDto participantCardsDto) {
        List<String> cardsInfo = participantCardsDto.cardsInfo().stream()
                .map(cardInfoDto -> cardInfoDto.shapeKoreanName() + cardInfoDto.number()).toList();

        String cardsInfoString = String.join(",", cardsInfo);
        return cardsInfoString;
    }

    public static void printInitialDealerCards(ParticipantCardsDto participantCardsDto) {
        CardInfoDto dealerCard = participantCardsDto.cardsInfo().getFirst();
        String cardInfoString = dealerCard.shapeKoreanName() + dealerCard.number();
        System.out.printf("%s: %s%n", participantCardsDto.name(), cardInfoString);
    }
}
