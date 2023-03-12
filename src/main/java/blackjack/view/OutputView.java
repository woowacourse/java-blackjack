package blackjack.view;

import blackjack.dto.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_DEFAULT_NAME = "딜러";

    private OutputView() {
    }

    public static void printParticipantsInitCards(final ParticipantCardsDTO cardsDTO) {
        List<ParticipantStatusDTO> participantCards = cardsDTO.getParticipantCards();
        System.out.println(String.format("%s와 %s에게 2장을 나누었습니다.", DEALER_DEFAULT_NAME,
                String.join(", ", cardsDTO.getPlayerNames()))
                + System.lineSeparator());
        String participantInitCards = participantCards.stream()
                .map(participantStatus -> getParticipantCards(participantStatus.getName(), participantStatus.getCards())
                        + System.lineSeparator())
                .collect(Collectors.joining());

        System.out.println(participantInitCards + System.lineSeparator());
    }

    public static void printDealerHit(final int hitCount) {
        System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n" + System.lineSeparator(), hitCount);
    }

    public static void printParticipantsCards(final ParticipantStatusDTO participantStatusDTO) {
        String playerCards = getParticipantCards(participantStatusDTO.getName(), participantStatusDTO.getCards());

        System.out.println(playerCards + System.lineSeparator());
    }

    public static void printParticipantCardWithResult(final ParticipantEntireStatusDTO entireStatusDTO) {
        ParticipantStatusDTO statusDTO = entireStatusDTO.getStatusDTO();
        String playerCards = getParticipantCards(statusDTO.getName(), statusDTO.getCards());
        String playerTotalPoint = String.format(" - 결과: %d", entireStatusDTO.getScore());
        System.out.println(playerCards + playerTotalPoint);
    }

    private static String getParticipantCards(final String participantName, final List<String> participantCards) {
        return participantName
                + " 카드: "
                + String.join(", ", participantCards);
    }

    public static void printBettingResults(ParticipantBettingResultDTO bettingResultDTO) {
        System.out.println("\n## 최종 수익");
        Set<Map.Entry<String, Integer>> bettingEntries = bettingResultDTO.getBettingResults().entrySet();
        String bettingResults = bettingEntries.stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(bettingResults);
    }
}
