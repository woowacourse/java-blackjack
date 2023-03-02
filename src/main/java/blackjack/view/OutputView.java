package blackjack.view;

import blackjack.dto.PlayerStatusDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String GIVE_START_CARD_COMPLETE_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String DEALER_CAN_PICK_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_CAN_NOT_PICK_MESSAGE = "딜러는 17이상이라 한장의 카드를 더 받지 못했습니다.";
    private static final String CARD = "카드";
    private static final String ITEM_DELIMITER = ", ";
    private static final String PLAYER_NAME_AND_CARDS_PARTITION = ": ";

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printStartStatus(PlayerStatusDto dealerStatus, List<PlayerStatusDto> challengersStatus) {
        printGivenMessage(dealerStatus, challengersStatus);
        printDealerStatus(dealerStatus);
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
        System.out.println(cards);
    }

    public static void printChallengersStatus(List<PlayerStatusDto> challengersStatus) {
        for (PlayerStatusDto challenger : challengersStatus) {
            printChallengerStatus(challenger);
        }
        System.out.println();
    }

    public static void printChallengerStatus(PlayerStatusDto challenger) {
        System.out.print(challenger.getName() + CARD);
        System.out.print(PLAYER_NAME_AND_CARDS_PARTITION);
        String cards = String.join(ITEM_DELIMITER, challenger.getCards());
        System.out.println(cards);
    }

    private static List<String> toChallengerNames(List<PlayerStatusDto> challengersStatus) {
        return challengersStatus.stream()
                .map(PlayerStatusDto::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public static void printDealerResult(boolean dealerCanPick) {
        if (dealerCanPick) {
            System.out.println(DEALER_CAN_PICK_MESSAGE);
            return;
        }
        System.out.println(DEALER_CAN_NOT_PICK_MESSAGE);
    }
}
