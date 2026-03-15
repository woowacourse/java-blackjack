package view;

import domain.dto.DealerResultDto;
import domain.dto.GameFinalResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEAL_MESSAGE = "{0}와 {1}에게 {2}장을 나누었습니다.";
    private static final String SHOW_HAND_MESSAGE = "{0}카드: {1}";
    private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public void printInitialInfo(GameInitialInfoDto initialInfo) {
        printHandOutNotice(initialInfo);
        printInitialHands(initialInfo);
    }

    public void printHand(List<String> hand, String name) {
        System.out.println(MessageFormat.format(
                SHOW_HAND_MESSAGE,
                name,
                String.join(", ", hand)
        ));
    }

    public void printDealerTurn() {
        System.out.println();
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public void printScoreResults(List<GameScoreResultDto> scoreResults) {
        System.out.println();
        for (GameScoreResultDto scoreResult : scoreResults) {
            System.out.println(MessageFormat.format(
                    SHOW_HAND_MESSAGE,
                    scoreResult.getPlayerName(),
                    String.join(", ", scoreResult.getHand())
                            + " - 결과: " + scoreResult.getScore()
            ));
        }
        System.out.println();
    }

    public void printFinalResult(GameFinalResultDto finalResult) {
        System.out.println("## 최종 수익");
        DealerResultDto dealerResult = finalResult.getDealerResult();
        printDealerResult(dealerResult);
        printPlayerResults(finalResult);
    }

    private void printInitialHands(GameInitialInfoDto initialInfo) {
        System.out.println(MessageFormat.format(
                SHOW_HAND_MESSAGE,
                initialInfo.getDealerName(),
                initialInfo.getDealerOpenCard()
        ));

        for (GameScoreResultDto result : initialInfo.getPlayerResults()) {
            System.out.println(MessageFormat.format(
                    SHOW_HAND_MESSAGE,
                    result.getPlayerName(),
                    String.join(", ", result.getHand())
            ));
        }
        System.out.println();
    }

    private void printDealerResult(DealerResultDto dealerResult) {
        System.out.println(dealerResult.getName() + ": " + dealerResult.getProfit());
    }

    private void printPlayerResults(GameFinalResultDto finalResult) {
        finalResult.getPlayerResults().forEach(
                (key, value) -> System.out.println(key + ": " + value)
        );
    }

    private void printHandOutNotice(GameInitialInfoDto initialInfo) {
        String playerNames = initialInfo.getPlayerResults().stream()
                .map(GameScoreResultDto::getPlayerName)
                .collect(Collectors.joining(", "));

        System.out.println(MessageFormat.format(
                DEAL_MESSAGE,
                initialInfo.getDealerName(),
                playerNames,
                initialInfo.getPlayerResults().stream()
                        .mapToInt(result -> result.getHand().size())
                        .max()
                        .orElse(0)
        ));
    }
}
