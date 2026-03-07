package view;

import constant.Result;
import domain.Player;
import dto.BlackjackResultDto;
import dto.DealerResultDto;
import dto.HandDto;
import dto.PlayerResultDto;
import dto.PlayersDto;
import java.util.List;

public class OutputView {

    private static final String PRINT_PLAYERS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String PRINT_HAND_MESSAGE = "%s카드: %s";
    private static final String PRINT_DEALER_HIT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PRINT_BLACKJACK_RESULT_MESSAGE = " - 결과: %d\n";
    private static final String PRINT_BLACKJACK_STATISTICS_HEADER_MESSAGE = "## 최종 승패";
    private static final String PRINT_BLACKJACK_STATISTICS_DEALER_MESSAGE = "딜러:%s%s%s\n";
    private static final String PRINT_BLACKJACK_STATISTICS_PLAYER_MESSAGE = "%s: %s\n";

    public void printPlayers(PlayersDto playersDto) {
        List<String> names = playersDto.players().stream()
            .map(Player::getName)
            .toList();
        System.out.printf(PRINT_PLAYERS_MESSAGE, String.join(", ", names)); // TODO: 상수?
    }

    public void printHandList(List<HandDto> handDtoList) {
        for (HandDto handDto : handDtoList) {
            printlnHand(handDto);
        }
        System.out.println();
    }

    public void printHand(HandDto handDto) {
        System.out.printf(PRINT_HAND_MESSAGE, handDto.name(), String.join(", ", handDto.hand())); // TODO: 상수?
    }

    public void printlnHand(HandDto handDto) {
        printHand(handDto);
        System.out.println();
    }

    public void printDealerHit() {
        System.out.println(PRINT_DEALER_HIT);
    }

    public void printBlackjackResult(List<BlackjackResultDto> blackjackResultDtoList) {
        for (BlackjackResultDto resultDto : blackjackResultDtoList) {
            printHand(resultDto.handDto());
            System.out.printf(PRINT_BLACKJACK_RESULT_MESSAGE, resultDto.score());
        }
        System.out.println();
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
