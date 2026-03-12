package view;

import dto.BlackJackInitStatusDto;
import dto.FinalResultDto;
import dto.HandDto;
import dto.ScoreResultDto;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INIT_NAME_HEADER_FORMAT = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String HAND_FORMAT = "%s카드 : %s\n";
    private static final String HAND_WITH_SCORE_FORMAT = "%s카드 : %s - 결과: %d\n";
    private static final String DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_HEADER = "\n## 최종 승패";
    private static final String FINAL_RESULT_DEALER = "딜러: ";

    public static void printInitMessage(BlackJackInitStatusDto blackJackInitStatusDto) {
        String playerNames = blackJackInitStatusDto.playerHands().stream()
                .map(HandDto::name)
                .collect(Collectors.joining(", "));
        System.out.printf(INIT_NAME_HEADER_FORMAT, playerNames);

        String dealerCards = String.join(", ", blackJackInitStatusDto.dealerHand().cardNames());
        System.out.printf(HAND_FORMAT, blackJackInitStatusDto.dealerHand().name(), dealerCards);

        for (HandDto playerHandDto : blackJackInitStatusDto.playerHands()) {
            System.out.printf(HAND_FORMAT, playerHandDto.name(), String.join(", ", playerHandDto.cardNames()));
        }
        System.out.println();
    }

    public static void printHandOutput(HandDto HandDto) {
        String cardNames = String.join(", ", HandDto.cardNames());
        System.out.printf(HAND_FORMAT, HandDto.name(), cardNames);
    }

    public static void printDealerHitMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public static void printFinalResult(FinalResultDto finalResultDto) {
        printScoreResult(finalResultDto);
        System.out.println(FINAL_RESULT_HEADER);
        System.out.println(FINAL_RESULT_DEALER + finalResultDto.dealerProfit());
        for(Map.Entry<String, Long> entry : finalResultDto.playerResults().entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void printScoreResult(FinalResultDto finalResultDto){
        System.out.println();
        for (ScoreResultDto dto : finalResultDto.scoreResultDtos()) {
            String name = dto.handDto().name();
            String cardNames = String.join(", ", dto.handDto().cardNames());
            int score = dto.score();
            System.out.printf(HAND_WITH_SCORE_FORMAT, name, cardNames, score);
        }
    }
}
