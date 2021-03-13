package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.user.BettingResult;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final int DEALER_OPEN_CARD_INDEX = 0;
    private static final String COMMA = ", ";

    public static void printInitialCards(User dealer, List<Player> players) {
        printIntroMessage(dealer, players);
        printUserCard(dealer, players);
        System.out.println();
    }

    private static void printIntroMessage(User dealer, List<Player> players) {
        String playerNames = players.stream()
            .map(User::getName)
            .collect(Collectors.joining(COMMA));
        System.out.printf("%n%s와 %s에게 2장의 나누었습니다.%n", dealer.getName(), playerNames);
    }

    public static void printUserCard(User dealer, List<Player> players) {
        printDealerCard(dealer);
        players.forEach(OutputView::printPlayerCard);
    }

    private static void printDealerCard(User dealer) {
        Card dealerOpenCard = dealer.getCards().get(DEALER_OPEN_CARD_INDEX);
        String dealerCards = dealerOpenCard.getDenomination().getName() + dealerOpenCard.getSuit().getName();
        System.out.printf("%s: %s%n", dealer.getName(), dealerCards);
    }

    public static void printPlayerCard(Player player) {
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

    public static void printWinningResult(List<BettingResult> bettingResults) {
        System.out.println("\n## 최종 수익");
        System.out.printf("딜러 : %.2f%n", calculateDealerResult(bettingResults));
        bettingResults.forEach(bettingResult ->
            System.out.printf("%s: %.2f%n", bettingResult.getName(), bettingResult.getEarningMoney()));
    }

    private static Double calculateDealerResult(List<BettingResult> bettingResults) {
        return -1.0 * bettingResults
            .stream()
            .mapToDouble(BettingResult::getEarningMoney)
            .sum();
    }
}
