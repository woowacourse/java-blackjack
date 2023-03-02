package blackjack.view;

import blackjack.dto.PlayerStatusDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String GIVE_START_CARD_COMPLETE_MESSAGE = "에게 2장을 나누었습니다.";
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

    private static void printDealerStatus(PlayerStatusDto dealerStatus) {
        System.out.print(dealerStatus.getName());
        System.out.print(PLAYER_NAME_AND_CARDS_PARTITION);
        String cards = String.join(ITEM_DELIMITER, dealerStatus.getCards());
        System.out.println(cards);
    }

    private static void printChallengersStatus(List<PlayerStatusDto> challengersStatus) {
        for (PlayerStatusDto challenger : challengersStatus) {
            System.out.print(challenger.getName() + CARD);
            System.out.print(PLAYER_NAME_AND_CARDS_PARTITION);
            String cards = String.join(ITEM_DELIMITER, challenger.getCards());
            System.out.println(cards);
        }
        System.out.println();
    }

    private static List<String> toChallengerNames(List<PlayerStatusDto> challengersStatus) {
        return challengersStatus.stream()
                .map(PlayerStatusDto::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
