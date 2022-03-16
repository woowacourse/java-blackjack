package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.CurrentCardsDto;
import blackjack.dto.ProfitDTO;
import blackjack.dto.TotalProfitDTO;
import blackjack.dto.TotalScoreDto;

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

        printCurrentStatus(dealer);
        for (CurrentCardsDto player : players) {
            printCurrentStatus(player);
        }

        System.out.println();
    }

    public static void printCurrentStatus(CurrentCardsDto dto) {
        System.out.println(makeCurrentCardToString(dto.getName(), dto.getCards()));
    }

    public static void printDealerAdded(String name, int bound) {
        System.out.println("\n" + name + "는 " + bound
                + "이하라 카드를 1장 더 받았습니다.");
    }

    public static void printTotalScore(List<TotalScoreDto> totalScoreDtos) {
        System.out.println();
        for (TotalScoreDto dto : totalScoreDtos) {
            System.out.println(makeCurrentCardToString(dto.getName(), dto.getCards())
                    + " - 결과: " + dto.getScore());
        }
    }

    public static void printTotalProfit(TotalProfitDTO totalProfit) {
        System.out.println("\n## 최종 수익");
        printProfit(totalProfit.getProfitOfDealer());
        for (ProfitDTO profit : totalProfit.getProfitOfPlayers()) {
            printProfit(profit);
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

    private static void printProfit(ProfitDTO profit) {
        System.out.println(profit.getName() + ": " + profit.getProfit());
    }

}
