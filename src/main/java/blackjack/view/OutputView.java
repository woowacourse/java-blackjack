package blackjack.view;

import blackjack.dto.ChallengerResultDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerStatusWithPointDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String GIVE_START_CARD_COMPLETE_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String DEALER_CAN_PICK_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_CAN_NOT_PICK_MESSAGE = "딜러는 17이상이라 한장의 카드를 더 받지 못했습니다.";
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
        printDealerOpenedStatus(dealerStatus);
        System.out.println();
        printChallengersStatus(challengersStatus);
    }

    private static void printGivenMessage(PlayerStatusDto dealerStatus, List<PlayerStatusDto> challengersStatus) {
        System.out.print(dealerStatus.getName() + "와 ");
        String challengerNames = String.join(ITEM_DELIMITER, toChallengerNames(challengersStatus));
        System.out.print(challengerNames);
        System.out.println(GIVE_START_CARD_COMPLETE_MESSAGE);
    }

    private static void printDealerOpenedStatus(PlayerStatusDto dealerStatus) {
        printDealerName(dealerStatus.getName());
        printOneCardFromDealer(dealerStatus.getOneCard());
    }

    private static void printDealerName(String name) {
        System.out.print(name);
        System.out.print(PLAYER_NAME_AND_CARDS_PARTITION);
    }

    private static void printOneCardFromDealer(String card) {
        System.out.print(card);
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

    public static void printDealerResult(boolean dealerCanPick) {
        System.out.println();
        if (dealerCanPick) {
            System.out.println(DEALER_CAN_PICK_MESSAGE);
            System.out.println();
            return;
        }
        System.out.println(DEALER_CAN_NOT_PICK_MESSAGE);
        System.out.println();
    }

    public static void printEndStatus(PlayerStatusWithPointDto dealerStatus, List<PlayerStatusWithPointDto> challengersStatus) {
        printDealerFinalStatus(dealerStatus);
        for (PlayerStatusWithPointDto challenger : challengersStatus) {
            printChallengerStatus(challenger);
            printPoint(challenger.getPoint());
        }
    }

    private static void printDealerFinalStatus(PlayerStatusWithPointDto dealerStatusDto) {
        printDealerName(dealerStatusDto.getName());
        printDealerCards(dealerStatusDto.getCards());
        printPoint(dealerStatusDto.getPoint());
    }

    private static void printDealerCards(List<String> cards) {
        String joinedCards = String.join(ITEM_DELIMITER, cards);
        System.out.print(joinedCards);
    }

    private static void printPoint(int point) {
        System.out.println(RESULT_PREFIX + point);
    }

    public static void printFinalRank(ChallengerResultDto challengerResultDto, DealerResultDto dealerResultDto) {
        System.out.println();
        System.out.println(FINAL_RESULT_HEADER_MESSAGE);
        printDealerFinalRank(dealerResultDto);
        printChallengersFinalRank(challengerResultDto);
    }

    private static void printDealerFinalRank(DealerResultDto dealerResultDto) {
        System.out.print(dealerResultDto.getName() + PLAYER_NAME_AND_CARDS_PARTITION);
        printDealerWinCount(dealerResultDto);
        printDealerDrawCount(dealerResultDto);
        printDealerLoseCount(dealerResultDto);
        System.out.println();
    }

    private static void printDealerWinCount(DealerResultDto dealerResultDto) {
        if (dealerResultDto.getWinCount() != 0) {
            System.out.print(dealerResultDto.getWinCount() + "승 ");
        }
    }

    private static void printDealerDrawCount(DealerResultDto dealerResultDto) {
        if (dealerResultDto.getDrawCount() != 0) {
            System.out.print(dealerResultDto.getDrawCount() + "무 ");
        }
    }

    private static void printDealerLoseCount(DealerResultDto dealerResultDto) {
        if (dealerResultDto.getLoseCount() != 0) {
            System.out.print(dealerResultDto.getLoseCount() + "패 ");
        }
    }

    private static void printChallengersFinalRank(ChallengerResultDto challengerResultDto) {
        Map<String, Integer> nameAndRanks = challengerResultDto.getNameAndProfits();
        for (String name : nameAndRanks.keySet()) {
            System.out.println(name + PLAYER_NAME_AND_CARDS_PARTITION + nameAndRanks.get(name));
        }
    }
}
