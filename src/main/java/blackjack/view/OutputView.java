package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitialCards(List<CurrentCardsDTO> dtos) {
        System.out.println(makeInitialDrawTitleString(dtos));

        for (CurrentCardsDTO dto : dtos) {
            printNameAndCard(dto.getName(), dto.getCards());
            System.out.println();
        }

        System.out.println();
    }

    private static String makeInitialDrawTitleString(List<CurrentCardsDTO> dtos) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append(dtos.get(0).getName())
                .append("와 ")
                .append(dtos.subList(1, dtos.size()).stream()
                        .map(CurrentCardsDTO::getName)
                        .collect(Collectors.joining(", ")))
                .append("에게 2장을 나누었습니다.\n");
        return sb.toString();
    }

    public static void printCurrentStatus(CurrentCardsDTO dto) {
        printNameAndCard(dto.getName(), dto.getCards());
        System.out.println();
    }

    public static void printTotalScore(List<TotalScoreDTO> totalScoreDTOs) {
        System.out.println();
        for (TotalScoreDTO dto : totalScoreDTOs) {
            printNameAndCard(dto.getName(), dto.getCards());
            System.out.print("- 결과: " + dto.getScore() + "\n");
        }
    }

    public static void printResult(DealerResultDTO dealerResult, List<PlayerResultDTO> playersResult) {
        System.out.println("\n## 최종 승패");
        System.out.println(dealerResult.getName() + ": " + dealerResult.getWinCount() + "승 " + dealerResult.getLoseCount() + "패");
        for (PlayerResultDTO playerResult : playersResult) {
            System.out.print(playerResult.getName() + ": ");
            if (playerResult.isWin()) {
                System.out.print("승\n");
                continue;
            }
            System.out.print("패\n");
        }
    }

    public static void printNameAndCard(String name, List<Card> cards) {
        StringBuilder result = new StringBuilder();

        result.append(name);
        result.append("카드: ");

        result.append(cards.stream()
                .map(c -> (c.getDenominationName() + c.getSymbolName()))
                .collect(Collectors.joining(", ")));

        System.out.print(result);
    }

    public static void printDealerAdded(String name) {
        System.out.println("\n" + name + "는 " + Dealer.BOUND_FOR_ADDITIONAL_CARD
                + "이하라 한장의 카드를 더 받았습니다.");
    }
}
