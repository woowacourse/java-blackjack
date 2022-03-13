package view;

import static domain.MatchResult.DRAW;
import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;

import domain.BlackJackResult;
import domain.MatchResult;
import domain.player.Dealer;
import dto.PlayerDto;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final long ZERO_FOR_DEFAULT = 0L;
    private static final String INFO_FOR_INITIAL_SPREAD = "%s와 %s에게 2장을 나누었습니다.%n";
    private static final String INFO_FOR_DEALER_ADD_CARD = "%s는 16이하라 한장의 카드를 더 받았습니다.%n";
    private static final String INFO_PLAYER_CARD_AND_SCORE = "%s카드: %s - 결과: %d%n";
    private static final String INFO_CARD_STATUS_AFTER_INITIAL_SPREAD = "%s: %s%n";
    private static final String COLON_FOR_JOINING_NAME_AND_CARD = ": ";
    private static final String RESULT_TITLE = "## 최종 승패";
    private static final String DEALER_RESULT_PRINT_TEMPLATE = "딜러: %d승 %d무 %d패%n";
    private static final String STRING_FOR_WIN = "승";
    private static final String STRING_FOR_LOSE = "패";
    private static final String STRING_FOR_DRAW = "무";

    private OutputView() {
    }

    public static void printSpreadAnnouncement(String dealerName, String playerNames) {
        System.out.println();
        System.out.printf(INFO_FOR_INITIAL_SPREAD, dealerName, playerNames);
    }

    public static void printSingleCardForDealer(PlayerDto dealerDto) {
        System.out.println(dealerDto.getName() + COLON_FOR_JOINING_NAME_AND_CARD + dealerDto.getFirstCardName());
    }

    public static void printTwoCardsForGamblers(List<PlayerDto> playerDtos) {
        playerDtos.forEach(
                playerDto -> System.out.printf(
                        INFO_CARD_STATUS_AFTER_INITIAL_SPREAD,
                        playerDto.getName(), playerDto.getJoinedCardNames())
        );
    }

    public static void printLineSeparator() {
        System.out.println();
    }

    public static void printCards(PlayerDto playerDto) {
        System.out.println(playerDto.getName() + COLON_FOR_JOINING_NAME_AND_CARD + playerDto.getJoinedCardNames());
    }

    public static void printDealerAddCard(Dealer dealer) {
        System.out.printf(INFO_FOR_DEALER_ADD_CARD, dealer.getName());
    }

    public static void printCardAndScore(PlayerDto playerDto) {
        System.out.printf(INFO_PLAYER_CARD_AND_SCORE,
                playerDto.getName(),
                playerDto.getJoinedCardNames(),
                playerDto.getScore());
    }

    public static void printResult(BlackJackResult blackJackResult) {
        System.out.println(System.lineSeparator() + RESULT_TITLE);
        printDealerResult(blackJackResult);
        blackJackResult.getPlayerResult()
                .forEach(OutputView::printSingleGamblerResult);
    }

    private static void printDealerResult(BlackJackResult blackJackResult) {
        Map<MatchResult, Long> dealerResult = blackJackResult.getDealerResult();
        System.out.printf(
                DEALER_RESULT_PRINT_TEMPLATE,
                dealerResult.getOrDefault(WIN, ZERO_FOR_DEFAULT),
                dealerResult.getOrDefault(DRAW, ZERO_FOR_DEFAULT),
                dealerResult.getOrDefault(LOSE, ZERO_FOR_DEFAULT)
        );
    }

    public static void printSingleGamblerResult(String name, MatchResult matchResult) {
        System.out.println(name + COLON_FOR_JOINING_NAME_AND_CARD + getStringByMatchResult(matchResult));
    }

    private static String getStringByMatchResult(MatchResult matchResult) {
        if (matchResult == WIN) {
            return STRING_FOR_WIN;
        }

        if (matchResult == LOSE) {
            return STRING_FOR_LOSE;
        }

        return STRING_FOR_DRAW;
    }
}
