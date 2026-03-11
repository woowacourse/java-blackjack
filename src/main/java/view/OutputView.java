package view;

import dto.BlackJackInitStatusDto;
import dto.FinalResultDto;
import dto.HandDto;
import dto.ScoreResultDto;

public class OutputView {

    private static final String DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    private static final String FINAL_RESULT_HEADER = "## 최종 승패";

    public static final String DEALER_COUNT_FORMAT = "딜러: %d승 %d패";
    public static final String PLAYER_RESULT_FORMAT = "%s: %s";

    public static void printInitMessage(BlackJackInitStatusDto blackJackInitStatusDto) {
        System.out.println();
        for (String message : blackJackInitStatusDto.initStatus()) {
            System.out.println(message);
        }
        System.out.println();
    }

    public static void printHandOutput(HandDto handDto) {
        String output = String.join(", ", handDto.cards());
        System.out.println(output);
    }

    public static void printDealerHitMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public static void printScoreResult(ScoreResultDto scoreResultDto) {
        System.out.println();
        for (String line : scoreResultDto.scoreResults()) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static void printFinalResult(FinalResultDto finalResultDto) {
        System.out.println(FINAL_RESULT_HEADER);
        System.out.printf((DEALER_COUNT_FORMAT) + "%n", finalResultDto.dealerWinCount(),
                finalResultDto.dealerLoseCount());

        finalResultDto.playerResults().forEach((key, value) ->
                System.out.printf(PLAYER_RESULT_FORMAT, key, value));
    }
}
