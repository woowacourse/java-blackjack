package blackjack.view;

import blackjack.domain.Result;
import blackjack.domain.TotalResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = System.getProperty("line.separator");

    public static void printInitialCardDistribution(Users users) {
        List<User> gameUsers = users.getUsers();
        printUserNames(gameUsers);
        System.out.println("에게 " + Deck.NUM_OF_INITIAL_CARDS + "장의 카드를 나누었습니다.\n");
        gameUsers.forEach(OutputView::printInitialCardStatus);
    }

    private static void printInitialCardStatus(User user) {
        System.out.println(showCardNames(user, user.getInitialCards()));
    }

    private static void printUserNames(List<User> gameUsers) {
        System.out.println(gameUsers.stream()
                .map(User::getName)
                .collect(Collectors.joining(DELIMITER)));
    }

    public static void printCardStatus(User user) {
        System.out.println(showCardNames(user, user.getCards()));
    }

    public static void printBusted(String name) {
        System.out.println(name + "이(가) 버스트 되었습니다");
    }

    public static void printDealerHitMoreCard() {
        System.out.println("딜러는 " + Dealer.THRESHOLD + "이하라 한 장의 카드를 더 받았습니다\n");
    }

    public static void printFinalCardScore(Users users) {
        System.out.println("\n## 결과 : \n" + users.getUsers().stream()
                .map(user -> showCardNames(user, user.getCards()) + " - 결과 : " + parseTotalScore(user))
                .collect(Collectors.joining(NEW_LINE)) + "\n");
    }

    private static String showCardNames(User user, List<Card> cards) {
        return user.getName() + " 카드: " + cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER));
    }

    private static String parseTotalScore(User user) {
        int finalScore = user.getTotalScore();
        if (finalScore == 0) {
            return "버스트";
        }
        return Integer.toString(finalScore);
    }

    public static void printFinalResult(TotalResult totalResult, Map<Result, Integer> dealerResult) {
        System.out.println("## 최종 승패");
        System.out.println(parseAllWinners(totalResult, dealerResult));
    }

    private static String parseAllWinners(TotalResult totalResult, Map<Result, Integer> dealerResult) {

        StringBuilder sb = new StringBuilder();

        sb.append(parseDealerTotalResult(dealerResult));
        totalResult.getTotalResult().forEach((player, result) ->
                sb.append(player.getName()).append(": ").append(result.getName()).append(NEW_LINE));
        return sb.toString();
    }

    private static String parseDealerTotalResult(Map<Result, Integer> playerResult) {
        return Dealer.DEALER + ": " +
                playerResult.get(Result.LOSE) + "승 " +
                playerResult.get(Result.DRAW) + "무 " +
                playerResult.get(Result.WIN) + "패" +
                NEW_LINE;
    }
}
