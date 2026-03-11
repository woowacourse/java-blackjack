package view;

import constant.PolicyConstant;
import constant.Result;
import dto.BlackjackResultDto;
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

    public void printPlayers(List<String> names) {
        System.out.printf(PRINT_PLAYERS_MESSAGE, String.join(PolicyConstant.DELIMITER + " ", names));
    }

    public void printPlayerList(List<ParticipantDto> participantDtoList) {
        for (ParticipantDto participantDto : participantDtoList) {
            printlnPlayer(participantDto);
        }
        System.out.println();
    }

    public void printDealerHit() {
        System.out.println(PRINT_DEALER_HIT);
    }

    public void printBlackjackResult(List<BlackjackResultDto> blackjackResultDtoList) {
        System.out.println();
        for (BlackjackResultDto resultDto : blackjackResultDtoList) {
            printPlayer(resultDto.name(), resultDto.hand());
            System.out.printf(PRINT_BLACKJACK_RESULT_MESSAGE, resultDto.score());
        }
        System.out.println();
    }

    public void printlnPlayer(ParticipantDto participantDto) {
        printPlayer(participantDto.name(), participantDto.hand());
        System.out.println();
    }

    private void printPlayer(String name, List<String> hand) {
        System.out.printf(PRINT_HAND_MESSAGE, name,
            String.join(PolicyConstant.DELIMITER + " ", hand));
    }

    public void printBlackjackStatistics(DealerResultDto dealerResultDto,
        List<PlayerResultDto> playerResultDtoList) {
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
