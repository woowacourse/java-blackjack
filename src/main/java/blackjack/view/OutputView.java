package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String START_ERROR = "[ERROR] ";

    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(START_ERROR + message);
    }

    public static void printFirstDistribute(CurrentCardsDto dealer, List<CurrentCardsDto> players) {
        System.out.println(makeFirstDistributeTitleString(dealer, players));

        for (CurrentCardsDto player : players) {
            printCurrentStatus(player);
        }

        System.out.println();
    }

    public static void printCurrentStatus(CurrentCardsDto dto) {
        System.out.println(makeCurrentCardToString(dto.getName(), dto.getCards()));
    }

    public static void printDealerAdded(String name) {
        System.out.println("\n" + name + "는 " + Dealer.BOUND_FOR_ADDITIONAL_CARD
                + "이하라 카드를 1장 더 받았습니다.");
    }

    public static void printTotalScore(List<TotalScoreDto> totalScoreDtos) {
        System.out.println();
        for (TotalScoreDto dto : totalScoreDtos) {
            System.out.println(makeCurrentCardToString(dto.getName(), dto.getCards())
                    + " - 결과: " + dto.getScore());
        }
    }

    public static void printTotalResult(TotalResultDto totalResult) {
        System.out.println("\n## 최종 승패");
        printDealerResult(totalResult.getDealerResult());
        for (PlayerResultDto playerResult : totalResult.getTotalPlayerResult()) {
            printPlayerResult(playerResult);
        }
    }

    private static String makeFirstDistributeTitleString(CurrentCardsDto dealer, List<CurrentCardsDto> players) {
        StringBuilder title = new StringBuilder("\n");
        title.append(dealer.getName())
                .append("와 ")
                .append(players.stream().map(CurrentCardsDto::getName)
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

    private static void printDealerResult(DealerResultDto dealerResult) {
        System.out.println(dealerResult.getName() + ": "
                + dealerResult.getWinCount() + "승 "
                + dealerResult.getLoseCount() + "패");
    }

    private static void printPlayerResult(PlayerResultDto playerResult) {
        System.out.print(playerResult.getName() + ": ");
        if (playerResult.isWin()) {
            System.out.print("승\n");
            return;
        }
        System.out.print("패\n");
    }
}
