package view;

import domain.game.Result;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.DealerStatisticDto;
import dto.ParticipantDto;
import dto.PlayerStatisticDto;
import java.util.List;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String PRINT_PLAYERS_MESSAGE = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String PRINT_HAND_MESSAGE = "%s카드: %s";
    private static final String PRINT_DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PRINT_BLACKJACK_RESULT_MESSAGE = " - 결과: %d\n";
    private static final String PRINT_BLACKJACK_STATISTICS_HEADER_MESSAGE = "## 최종 승패";
    private static final String PRINT_BLACKJACK_STATISTICS_DEALER_MESSAGE = "딜러:%s%s%s\n";
    private static final String PRINT_BLACKJACK_STATISTICS_PLAYER_MESSAGE = "%s: %s\n";

    public void printPlayers(List<ParticipantDto> playerDtoList) {
        List<String> names = playerDtoList.stream()
            .map(ParticipantDto::name)
            .toList();
        System.out.printf(PRINT_PLAYERS_MESSAGE, String.join(DELIMITER, names));
    }

    public void printHandList(ParticipantDto dealerDto, List<ParticipantDto> playerDtoList) {
        printlnHand(dealerDto.name(), dealerDto.hand());
        for (ParticipantDto playerDto : playerDtoList) {
            printlnHand(playerDto.name(), playerDto.hand());
        }
        System.out.println();
    }

    public void printHand(String name, List<String> hand) {
        System.out.printf(PRINT_HAND_MESSAGE, name, String.join(DELIMITER + " ", hand));
    }

    public void printlnHand(String name, List<String> hand) {
        printHand(name, hand);
        System.out.println();
    }

    public void printDealerHit() {
        System.out.println(PRINT_DEALER_HIT);
    }

    public void printBlackjackResult(BlackjackResultDto blackjackResult) {
        ParticipantDto dealerResultDto = blackjackResult.dealerResultDto();
        List<ParticipantDto> playerResultDtoList = blackjackResult.playerResultDtoList();
        System.out.println();
        printHand(dealerResultDto.name(), dealerResultDto.hand());
        System.out.printf(PRINT_BLACKJACK_RESULT_MESSAGE, dealerResultDto.score());
        for (ParticipantDto playerResultDto : playerResultDtoList) {
            printHand(playerResultDto.name(), playerResultDto.hand());
            System.out.printf(PRINT_BLACKJACK_RESULT_MESSAGE, playerResultDto.score());
        }
        System.out.println();
    }

    public void printBlackjackStatistics(BlackjackStatisticsDto blackjackStatistics) {
        DealerStatisticDto dealerStatisticDto = blackjackStatistics.dealerStatisticDto();
        List<PlayerStatisticDto> playerStatisticDtoList = blackjackStatistics.playerStatisticDtoList();
        System.out.println(PRINT_BLACKJACK_STATISTICS_HEADER_MESSAGE);
        System.out.printf(PRINT_BLACKJACK_STATISTICS_DEALER_MESSAGE,
            printResult(dealerStatisticDto.win(), Result.WIN.getResult()),
            printResult(dealerStatisticDto.draw(), Result.DRAW.getResult()),
            printResult(dealerStatisticDto.lose(), Result.LOSE.getResult()));
        for (PlayerStatisticDto playerStatisticDto : playerStatisticDtoList) {
            System.out.printf(PRINT_BLACKJACK_STATISTICS_PLAYER_MESSAGE, playerStatisticDto.name(),
                playerStatisticDto.result().getResult());
        }
    }

    private String printResult(int result, String name) {
        if (result == 0) {
            return "";
        }
        return " " + result + name;
    }
}
