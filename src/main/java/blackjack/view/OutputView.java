package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.currentCards.CurrentCardsDto;
import blackjack.dto.currentCards.TotalCurrentCardsDto;
import blackjack.dto.profit.ProfitDto;
import blackjack.dto.profit.TotalProfitDto;
import blackjack.dto.score.ScoreDto;
import blackjack.dto.score.TotalScoreDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String START_ERROR = "[ERROR] ";

    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(START_ERROR + message);
    }

    public static void printFirstDistribute(TotalCurrentCardsDto totalCurrentCards) {
        CurrentCardsDto dealer = totalCurrentCards.getCurrentCardsOfDealer();
        List<CurrentCardsDto> players = totalCurrentCards.getCurrentCardsOfPlayers();

        printFirstDistributeTitle(dealer.getName(), players.stream()
                .map(CurrentCardsDto::getName)
                .collect(Collectors.toList()));

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

    public static void printTotalScore(TotalScoreDto totalScore) {
        System.out.println();
        for (ScoreDto score : totalScore.getTotalScore()) {
            System.out.println(makeCurrentCardToString(score.getName(), score.getCards())
                    + " - 결과: " + score.getScore());
        }
    }

    public static void printTotalProfit(TotalProfitDto totalProfit) {
        System.out.println("\n## 최종 수익");
        printProfit(totalProfit.getProfitOfDealer());
        for (ProfitDto profit : totalProfit.getProfitOfPlayers()) {
            printProfit(profit);
        }
    }

    private static void printFirstDistributeTitle(String dealer, List<String> players) {
        StringBuilder title = new StringBuilder("\n");
        title.append(dealer)
                .append("와 ")
                .append(String.join(", ", players))
                .append("에게 2장을 나누었습니다.\n");

        System.out.println(title);
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

    private static void printProfit(ProfitDto profit) {
        System.out.println(profit.getName() + ": " + profit.getProfit());
    }

}
