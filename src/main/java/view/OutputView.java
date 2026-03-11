package view;

import constant.PolicyConstant;
import constant.Result;
import dto.BlackjackStatisticsDto;
import dto.DealerResultDto;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.List;

public class OutputView {

    private static final String PRINT_PLAYERS_MESSAGE = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String PRINT_HAND_MESSAGE = "%s카드: %s";
    private static final String PRINT_DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PRINT_BLACKJACK_RESULT_MESSAGE = " - 결과: %d\n";
    private static final String PRINT_BLACKJACK_STATISTICS_HEADER_MESSAGE = "## 최종 승패";
    private static final String PRINT_BLACKJACK_STATISTICS_DEALER_MESSAGE = "딜러:%s%s%s\n";
    private static final String PRINT_BLACKJACK_STATISTICS_PLAYER_MESSAGE = "%s: %s\n";

    public void printPlayers(List<ParticipantDto> participantDtoList) {
        List<String> names = participantDtoList.stream()
            .map(ParticipantDto::name)
            .filter(name -> !name.equals(PolicyConstant.DEALER_NAME))
            .toList();
        System.out.printf(PRINT_PLAYERS_MESSAGE, String.join(PolicyConstant.DELIMITER + " ", names));
    }

    public void printHandList(List<ParticipantDto> participantDtoList) {
        for (ParticipantDto participantDto : participantDtoList) {
            printlnHand(participantDto.name(), participantDto.hand());
        }
        System.out.println();
    }

    public void printHand(String name, List<String> hand) {
        System.out.printf(PRINT_HAND_MESSAGE, name, String.join(PolicyConstant.DELIMITER + " ", hand));
    }

    public void printlnHand(String name, List<String> hand) {
        printHand(name, hand);
        System.out.println();
    }

    public void printDealerHit() {
        System.out.println(PRINT_DEALER_HIT);
    }

    public void printBlackjackResult(List<ParticipantDto> blackjackResult) {
        System.out.println();
        for (ParticipantDto participantDto : blackjackResult) {
            printHand(participantDto.name(), participantDto.hand());
            System.out.printf(PRINT_BLACKJACK_RESULT_MESSAGE, participantDto.score());
        }
        System.out.println();
    }

    public void printBlackjackStatistics(BlackjackStatisticsDto blackjackStatistics) {
        DealerResultDto dealerResultDto = blackjackStatistics.dealerResultDto();
        List<PlayerResultDto> playerResultDtoList = blackjackStatistics.playerResultDtoList();
        System.out.println(PRINT_BLACKJACK_STATISTICS_HEADER_MESSAGE);
        System.out.printf(PRINT_BLACKJACK_STATISTICS_DEALER_MESSAGE,
            printResult(dealerResultDto.win(), Result.WIN.getResult()),
            printResult(dealerResultDto.draw(), Result.DRAW.getResult()),
            printResult(dealerResultDto.lose(), Result.LOSE.getResult()));
        for (PlayerResultDto playerResultDto : playerResultDtoList) {
            System.out.printf(PRINT_BLACKJACK_STATISTICS_PLAYER_MESSAGE, playerResultDto.name(),
                playerResultDto.result().getResult());
        }
    }

    private String printResult(int result, String name) {
        if (result == 0) {
            return "";
        }
        return " " + result + name;
    }
}
