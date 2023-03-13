package blackjack.view;

import blackjack.dto.ChallengerResultDto;
import blackjack.dto.PlayerStatusDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String GIVE_START_CARD_COMPLETE_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String DEALER_CAN_PICK_MESSAGE = "딜러는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_CAN_NOT_PICK_MESSAGE = "딜러는 %d이상이라 한장의 카드를 더 받지 못했습니다.";
    private static final String FINAL_RESULT_HEADER_MESSAGE = "## 최종 승패";
    private static final String CARD = "카드";
    private static final String ITEM_DELIMITER = ", ";
    private static final String PLAYER_NAME_AND_CARDS_PARTITION = ": ";
    private static final String RESULT_PREFIX = " - 결과: ";

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printStartStatus(PlayerStatusDto dealerStatus, List<PlayerStatusDto> challengersStatus) {
        System.out.println();
        printGivenMessage(dealerStatus, challengersStatus);
        printDealerStatus(dealerStatus);
        System.out.println();
        printChallengersStatus(challengersStatus);
    }

    private static void printGivenMessage(PlayerStatusDto dealerStatus, List<PlayerStatusDto> challengersStatus) {
        System.out.print(dealerStatus.getName() + "와 ");
        String challengerNames = String.join(ITEM_DELIMITER, toChallengerNames(challengersStatus));
        System.out.print(challengerNames);
        System.out.println(GIVE_START_CARD_COMPLETE_MESSAGE);
    }

    public static void printDealerStatus(PlayerStatusDto dealerStatus) {
        System.out.print(dealerStatus.getName());
        System.out.print(PLAYER_NAME_AND_CARDS_PARTITION);
        String cards = String.join(ITEM_DELIMITER, dealerStatus.getCards());
        System.out.print(cards);
    }

    public static void printChallengersStatus(List<PlayerStatusDto> challengersStatus) {
        for (PlayerStatusDto challenger : challengersStatus) {
            printChallengerStatusInGame(challenger);
        }
    }

    public static void printChallengerStatusInGame(PlayerStatusDto playerStatusDto) {
        printChallengerStatus(playerStatusDto);
        System.out.println();
    }

    public static void printChallengerStatus(PlayerStatusDto challenger) {
        System.out.print(challenger.getName() + CARD);
        System.out.print(PLAYER_NAME_AND_CARDS_PARTITION);
        String cards = String.join(ITEM_DELIMITER, challenger.getCards());
        System.out.print(cards);
    }

    private static List<String> toChallengerNames(List<PlayerStatusDto> challengersStatus) {
        return challengersStatus.stream()
                .map(PlayerStatusDto::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public static void printDealerTurnResult(boolean dealerCanPick, int dealerMaximumPoint) {
        System.out.println();
        if (dealerCanPick) {
            System.out.println(String.format(DEALER_CAN_PICK_MESSAGE, dealerMaximumPoint));
            System.out.println();
            return;
        }
        System.out.println(String.format(DEALER_CAN_NOT_PICK_MESSAGE, dealerMaximumPoint + 1));
        System.out.println();
    }

    public static void printEndStatus(PlayerStatusDto dealerStatus, List<PlayerStatusDto> challengersStatus) {
        printDealerStatus(dealerStatus);
        printPoint(dealerStatus.getPoint());
        for (PlayerStatusDto challenger : challengersStatus) {
            printChallengerStatus(challenger);
            printPoint(challenger.getPoint());
        }
    }

    private static void printPoint(int point) {
        System.out.println(RESULT_PREFIX + point);
    }

    public static void printRevenue(ChallengerResultDto challengerResultDto) {
        System.out.println();
        System.out.println(FINAL_RESULT_HEADER_MESSAGE);
        Map<String, Integer> nameAndResult = challengerResultDto.getNameAndResult();
        for (String name : nameAndResult.keySet()) {
            System.out.println(name + PLAYER_NAME_AND_CARDS_PARTITION + nameAndResult.get(name));
        }
    }
}
