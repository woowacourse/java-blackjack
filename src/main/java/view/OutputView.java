package view;

import domain.Player;
import domain.constant.Result;
import domain.dto.GameFinalResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;

import java.text.MessageFormat;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEAL_MESSAGE = "{0}와 {1}에게 {2}장을 나누었습니다.";
    private static final String SHOW_HAND_MESSAGE = "{0}카드: {1}";
    private static final String FINAL_RESULT_MESSAGE = "{0}: {1}";
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

    public void printFinalResult(List<GameFinalResultDto> finalResult) {
        System.out.println("## 최종 승패");
        GameFinalResultDto firstPlayer = finalResult.removeFirst();
        Map<Result, Integer> resultCounts = new EnumMap<>(Result.class);

        countDealerResult(finalResult, resultCounts);
        printDealerResult(firstPlayer, resultCounts);
        printPlayerResult(finalResult);
    }

    private void countDealerResult(List<GameFinalResultDto> finalResult, Map<Result, Integer> resultCounts) {
        for (GameFinalResultDto result : finalResult) {
            if (result.getResult() == Result.WIN) {
                resultCounts.put(Result.LOSE, resultCounts.getOrDefault(Result.LOSE, 0) + 1);
                continue;
            }

            if (result.getResult() == Result.LOSE) {
                resultCounts.put(Result.WIN, resultCounts.getOrDefault(Result.WIN, 0) + 1);
                continue;
            }

            resultCounts.put(result.getResult(), resultCounts.getOrDefault(result.getResult(), 0) + 1);
        }
    }

    private void printDealerResult(GameFinalResultDto firstPlayer, Map<Result, Integer> resultCounts) {
        StringBuilder sb = new StringBuilder();
        sb.append(firstPlayer.getName()).append(": ");
        for (Result result : resultCounts.keySet()) {
            sb.append(resultCounts.get(result)).append(result.getName());
        }
        System.out.println(sb);
    }

    private void printPlayerResult(List<GameFinalResultDto> finalResult) {
        for (GameFinalResultDto result : finalResult) {
            System.out.println(MessageFormat.format(
                    FINAL_RESULT_MESSAGE,
                    result.getName(),
                    result.getResult().getName()
            ));
        }
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
}
