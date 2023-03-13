package view;

import controller.HandDto;
import controller.HandScoreDto;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String NAME_CARD_DELIMITER = ":";

    private static final String CARD_DELIMITER = ", ";

    public void printInitialHands(final List<HandDto> handDtos) {
        printInitialState(handDtos);
        printInitialCards(handDtos);
    }

    private void printInitialState(final List<HandDto> handDtos) {
        final String initialState = handDtos.stream()
                                            .map(HandDto::name)
                                            .collect(joining(CARD_DELIMITER, "", "에게 2장을 나누었습니다."));
        System.out.println(initialState);
    }

    private void printInitialCards(final List<HandDto> handDtos) {
        handDtos.forEach(handDto -> {
            if (handDto.name()
                       .equals("딜러")) {
                printFirstCard(handDto);
                return;
            }
            printCards(handDto);
        });
    }

    private void printFirstCard(final HandDto handDto) {
        System.out.println(generateCardsInfo(handDto.name(), handDto.cards()
                                                                    .subList(0, 1)));
    }

    public void printCards(final HandDto handDto) {
        System.out.println(generateCardsInfo(handDto.name(), handDto.cards()));
    }

    private String generateCardsInfo(final String name, final List<String> cards) {
        final String cardsInfo = String.join(CARD_DELIMITER, cards);
        return String.format("%s%s%s", name, NAME_CARD_DELIMITER, cardsInfo);
    }

    public void printIfDealerReceivedCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printHandsWithScore(final List<HandScoreDto> handScoreDtos) {
        for (final HandScoreDto handScoreDto : handScoreDtos) {
            System.out.println(generateCardsAndScore(handScoreDto));
        }
    }

    private String generateCardsAndScore(final HandScoreDto participantDtoWithScore) {
        final String name = participantDtoWithScore.name();
        final List<String> cards = participantDtoWithScore.cards();
        final int score = participantDtoWithScore.score();
        return String.format("%s - 결과: %d", generateCardsInfo(name, cards), score);
    }

    public void printEarningsInfo(final int dealerEarning, final Map<String, Integer> playerEarnings) {
        System.out.println("## 최종 수익");
        printDealerEarning(dealerEarning);
        playerEarnings.forEach((name, earning) -> System.out.println(name + ": " + earning));
    }

    private void printDealerEarning(final int dealerEarning) {
        System.out.println("딜러: " + dealerEarning);
    }
}
