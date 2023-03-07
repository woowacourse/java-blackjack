package view;

import controller.HandDto;
import controller.HandScoreDto;
import domain.GameOutcome;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class OutputView {
    private static final String NAME_CARD_DELIMITER = ":";

    private static final String CARD_DELIMITER = ", ";

    public void printInitialHands(List<HandDto> handDtos) {
        printInitialState(handDtos);
        printInitialCards(handDtos);
    }

    private void printInitialState(List<HandDto> handDtos) {
        String initialState = handDtos.stream()
                                      .map(HandDto::name)
                                      .collect(joining(CARD_DELIMITER, "", "에게 2장을 나누었습니다."));
        System.out.println(initialState);
    }

    private void printInitialCards(List<HandDto> handDtos) {
        handDtos.forEach(handDto -> {
            if (handDto.name()
                       .equals("딜러")) {
                printFirstCard(handDto);
                return;
            }
            printCards(handDto);
        });
    }

    private void printFirstCard(HandDto handDto) {
        System.out.println(generateCardsInfo(handDto.name(), handDto.cards()
                                                                    .subList(0, 1)));
    }

    public void printCards(HandDto handDto) {
        System.out.println(generateCardsInfo(handDto.name(), handDto.cards()));
    }

    private String generateCardsInfo(String name, List<String> cards) {
        String cardsInfo = String.join(CARD_DELIMITER, cards);
        return String.format("%s%s%s", name, NAME_CARD_DELIMITER, cardsInfo);
    }

    public void printIfDealerReceivedCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printHandsWithScore(List<HandScoreDto> participantDtosWithScore) {
        for (HandScoreDto participantDtoWithScore : participantDtosWithScore) {
            System.out.println(generateCardsAndScore(participantDtoWithScore));
        }
    }

    private String generateCardsAndScore(HandScoreDto participantDtoWithScore) {
        String name = participantDtoWithScore.name();
        List<String> cards = participantDtoWithScore.cards();
        int score = participantDtoWithScore.score();
        return String.format("%s - 결과: %d", generateCardsInfo(name, cards), score);
    }

    public void printWinLossInfo(Map<String, GameOutcome> playerOutcomes) {
        System.out.println("## 최종 승패");
        Map<GameOutcome, Long> dealerOutcome = generateDealerOutcome(playerOutcomes);
        printDealerOutcome(dealerOutcome);
        playerOutcomes.forEach((name, outcome) -> System.out.println(name + ": " + outcome.value()));
    }

    private Map<GameOutcome, Long> generateDealerOutcome(Map<String, GameOutcome> playerOutcomes) {
        return playerOutcomes.values()
                             .stream()
                             .collect(groupingBy(GameOutcome::oppositeOutcome, counting()));
    }

    private void printDealerOutcome(Map<GameOutcome, Long> dealerOutcome) {
        System.out.print("딜러:");
        dealerOutcome.forEach((outcome, count) -> System.out.print(" " + count + outcome.value()));
        System.out.println();
    }
}
