package view;

import dto.BlackJackHandDto;
import dto.BlackJackInitStatusDto;
import dto.FinalResultDto;
import dto.ScoreResultDto;

public class OutputView {

    private static final String DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
    private static final String FINAL_RESULT_HEADER = "## 최종 승패";

    public static void printInitMessage(BlackJackInitStatusDto blackJackInitStatusDto) {
        System.out.println();
        for (String message : blackJackInitStatusDto.initStatus()) {
            System.out.println(message);
        }
        System.out.println();
    }

    public static void printHandOutput(BlackJackHandDto blackJackHandDto) {
        System.out.println(blackJackHandDto.handOutput());
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
        for (String line : finalResultDto.finalResults()) {
            System.out.println(line);
        }
    }
}
