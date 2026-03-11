package view;

import dto.ParticipantCardsDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printGameInitialMessage(List<String> playersNames) {
        String playersNamesMessage = String.join(",", playersNames);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", playersNamesMessage);
    }

    public static void printCards(ParticipantCardsDto participantCardsDto) {
        String cardsInfoMessage = String.join(",", participantCardsDto.cardsInfo());
        System.out.printf("%s: %s%n", participantCardsDto.name(), cardsInfoMessage);
    }

    public static void printFinalCards(ParticipantCardsDto participantCardsDto) {
        String cardsInfoMessage = String.join(",", participantCardsDto.cardsInfo());
        System.out.println(
                participantCardsDto.name() + ": " + cardsInfoMessage + " - 결과: " + participantCardsDto.totalScore());
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
                resultMessage.append(entry.getKey()).append(": ").append("승%n");
                winCount += 1;
                continue;
            }

            resultMessage.append(entry.getKey()).append(": ").append("패%n");
            loseCount += 1;
        }

        System.out.println("## 최종 승패");
        System.out.println("딜러: " + loseCount + "승" + " " + winCount + "패");
        System.out.printf(resultMessage.toString());
    }

    public static void printInitialPlayerCards(ParticipantCardsDto participantCardsDto) {
        String cardsInfoMessage = String.join(",", participantCardsDto.cardsInfo());
        System.out.printf("%s: %s%n", participantCardsDto.name(), cardsInfoMessage);

    }

    public static void printInitialDealerCards(ParticipantCardsDto participantCardsDto) {
        String dealerCard = participantCardsDto.cardsInfo().getFirst();
        System.out.printf("%s: %s%n", participantCardsDto.name(), dealerCard);

    }
}
