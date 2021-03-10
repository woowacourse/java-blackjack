package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.user.MatchResult;
import blackjack.domain.user.User;
import blackjack.domain.user.WinningResult;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class OutputView {
    private static final String COMMA = ", ";
    public static final int DEALER_OPEN_CARD_INDEX = 0;

    public static void printInitialCards(User dealer, List<User> players) {
        printIntroMessage(dealer, players);
        printUserCard(dealer, players);
        System.out.println();
    }

    private static void printIntroMessage(User dealer, List<User> players) {
        String playerNames = players.stream()
            .map(User::getName)
            .collect(Collectors.joining(COMMA));
        System.out.printf("%n%s와 %s에게 2장의 나누었습니다.%n", dealer.getName(), playerNames);
    }

    public static void printUserCard(User dealer, List<User> players) {
        printDealerCard(dealer);
        players.forEach(OutputView::printPlayerCard);
    }

    private static void printDealerCard (User dealer) {
        Card dealerOpenCard = dealer.getCards().get(DEALER_OPEN_CARD_INDEX);
        String dealerCards = dealerOpenCard.getDenomination().getName() + dealerOpenCard.getSuit().getName();
        System.out.printf("%s: %s%n", dealer.getName(), dealerCards);
    }

    public static void printPlayerCard(User player) {
        String cardString = makeCardString(player.getCards());
        System.out.printf("%s의 카드: %s%n", player.getName(), cardString);
    }

    private static String makeCardString(List<Card> cards) {
        return cards.stream()
            .map(card -> String.format("%s%s", card.getDenomination().getName(), card.getSuit().getName()))
            .collect(Collectors.joining(COMMA));
    }

    public static void printDealerDraw(boolean hasDrawn) {
        if (hasDrawn) {
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
            return;
        }

        System.out.println("\n딜러는 16초과로 카드를 받지 않았습니다.\n");
    }

    public static void printUserResult(List<User> users) {
        users.forEach(OutputView::printScoreResult);
    }

    private static PrintStream printScoreResult(User user) {
        return System.out.printf("%s 카드 : %s - 결과: %d%n",
            user.getName(),
            makeCardString(user.getCards()),
            user.getScore());
    }

    public static void printWinningResult(List<WinningResult> winningResults) {
        System.out.println("\n## 최종 승패");
        System.out.println("딜러 : " + calculateDealerResult(winningResults));
        winningResults.forEach(winningResult ->
            System.out.printf("%s: %s%n", winningResult.getName(), winningResult.getResult().getName()));
    }

    private static String calculateDealerResult(List<WinningResult> winningResults) {
        Map<MatchResult, Long> calculate = countEachResults(winningResults);

        return calculate.keySet().stream()
            .filter(matchResult -> calculate.get(matchResult) > 0)
            .map(matchResult -> String.format("%d%s", calculate.get(matchResult), matchResult.getReverseName()))
            .collect(Collectors.joining(" "));
    }

    public static Map<MatchResult, Long> countEachResults(List<WinningResult> winningResults) {
        return winningResults.stream()
            .map(WinningResult::getResult)
            .collect(groupingBy(Function.identity(), counting()));
    }
}
