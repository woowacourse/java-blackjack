package view;

import static java.util.stream.Collectors.joining;

import controller.ParticipantDto;
import controller.ParticipantDtoWithScore;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String NAME_INFO_DELIMITER = ": ";

    private static final String CARD_DELIMITER = ", ";

    public void printParticipantsInitialCards(List<ParticipantDto> participantDtos) {
        printInitialState(participantDtos);
        printInitialCards(participantDtos);
    }

    private void printInitialState(List<ParticipantDto> participantDtos) {
        String initialState = participantDtos.stream()
                .map(ParticipantDto::name)
                .collect(joining(CARD_DELIMITER, "", "에게 2장을 나누었습니다."));
        System.out.println(initialState + System.lineSeparator());
    }

    private void printInitialCards(List<ParticipantDto> participantDtos) {
        participantDtos.forEach(participantDto -> {
            if (participantDto.name().equals("딜러")) {
                printFirstCard(participantDto);
                return;
            }
            printAllCards(participantDto);
        });
        System.out.println();
    }

    private void printFirstCard(ParticipantDto participantDto) {
        System.out.println(generateCardsInfo(participantDto.name(), participantDto.cards().subList(0, 1)));
    }

    public void printAllCards(ParticipantDto participantDto) {
        System.out.println(generateCardsInfo(participantDto.name(),
                participantDto.cards()));
    }

    private String generateCardsInfo(String name, List<String> cards) {
        String cardsInfo = String.join(CARD_DELIMITER, cards);
        return String.format("%s%s%s", name, NAME_INFO_DELIMITER, cardsInfo);
    }

    public void printDealerHandOutInfo() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printParticipantsScore(List<ParticipantDtoWithScore> participantDtosWithScore) {
        System.out.println();
        for (ParticipantDtoWithScore participantDtoWithScore : participantDtosWithScore) {
            System.out.println(generateCardsAndScore(participantDtoWithScore));
        }
    }

    private String generateCardsAndScore(ParticipantDtoWithScore participantDtoWithScore) {
        String name = participantDtoWithScore.name();
        List<String> cards = participantDtoWithScore.cards();
        int score = participantDtoWithScore.score();
        return String.format("%s - 결과: %d", generateCardsInfo(name, cards), score);
    }

    public void printRevenues(Map<String, Integer> playerOutcomes) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        int dealerOutcome = calculateDealerRevenue(playerOutcomes);
        printDealerOutcome(dealerOutcome);
        playerOutcomes.forEach((name, outcome) -> System.out.println(name + NAME_INFO_DELIMITER + outcome));
    }

    private int calculateDealerRevenue(Map<String, Integer> playerOutcomes) {
        return playerOutcomes.values().stream().mapToInt(Integer::intValue)
                .sum() * -1;
    }

    private void printDealerOutcome(int dealerOutcome) {
        System.out.println("딜러" + NAME_INFO_DELIMITER + dealerOutcome);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
