package blackjack.view;

import blackjack.domain.*;
import blackjack.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printFirstDistribute(List<CurrentCardsDTO> currentCardsDTOs) {
        System.out.println(makeFirstDistributeTitleString(currentCardsDTOs));

        for (CurrentCardsDTO dto : currentCardsDTOs) {
            System.out.println(makeCurrentCardToString(dto.getName(), dto.getCards()));
        }

        System.out.println();
    }

    public static void printCurrentStatus(CurrentCardsDTO dto) {
        System.out.println(makeCurrentCardToString(dto.getName(), dto.getCards()));
    }

    public static void printDealerAdded(String name) {
        System.out.println("\n" + name + "는 " + Dealer.BOUND_FOR_ADDITIONAL_CARD
                + "이하라 카드를 더 받았습니다.");
    }

    public static void printTotalScore(List<TotalScoreDTO> totalScoreDTOs) {
        System.out.println();
        for (TotalScoreDTO dto : totalScoreDTOs) {
            System.out.println(makeCurrentCardToString(dto.getName(), dto.getCards())
                    + " - 결과: " + dto.getScore());
        }
    }

    public static void printTotalResult(TotalResultDTO totalResult) {
        System.out.println("\n## 최종 승패");
        printDealerResult(totalResult.getDealerResult());
        for (PlayerResultDTO playerResult : totalResult.getTotalPlayerResult()) {
            printPlayerResult(playerResult);
        }
    }

    private static String makeFirstDistributeTitleString(List<CurrentCardsDTO> dtos) {
        StringBuilder title = new StringBuilder("\n");

        title.append(dtos.get(0).getName())
                .append("와 ")
                .append(dtos.subList(1, dtos.size()).stream()
                        .map(CurrentCardsDTO::getName)
                        .collect(Collectors.joining(", ")))
                .append("에게 2장을 나누었습니다.\n");

        return title.toString();
    }

    private static String makeCurrentCardToString(String name, List<Card> cards) {
        StringBuilder result = new StringBuilder();

        result.append(name)
                .append(" 카드: ")
                .append(cards.stream()
                        .map(c -> (c.getDenominationName() + c.getSymbolName()))
                        .collect(Collectors.joining(", ")));

        return result.toString();
    }

    private static void printDealerResult(DealerResultDTO dealerResult) {
        System.out.println(dealerResult.getName() + ": "
                + dealerResult.getWinCount() + "승 "
                + dealerResult.getLoseCount() + "패");
    }

    private static void printPlayerResult(PlayerResultDTO playerResult) {
        System.out.print(playerResult.getName() + ": ");
        if (playerResult.isWin()) {
            System.out.print("승\n");
            return;
        }
        System.out.print("패\n");
    }
}
