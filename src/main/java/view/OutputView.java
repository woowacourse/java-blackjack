package view;

import domain.card.Card;
import domain.game.Result;
import domain.game.Results;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String INIT_DISTRIBUTE_COUNT = "2";

    public static void printDistributeMessage(final Users users) {
        List<String> usersName = new ArrayList<>();

        users.forEach(User -> usersName.add(User.getName()));
        System.out.println("\n딜러와 " + String.join(DELIMITER, usersName) + "에게 " + INIT_DISTRIBUTE_COUNT + "장 나누었습니다.");
    }

    public static void printInitStatus(final Dealer dealer, final Users users) {
        printStatus(dealer);
        users.forEach(User -> printStatus(User));
        System.out.println();
    }

    public static void printStatus(final Player player) {
        if (player instanceof Dealer) {
            System.out.printf("%s카드: %s\n", player.getName(), player.getFirstCardInfo());
            return;
        }
        System.out.printf("%s카드: %s\n", player.getName(), makeCardNames(player.getCards()));
    }

    private static String makeCardNames(final List<Card> cards) {
        return cards.stream()
                .map(Card::getCardInfo)
                .collect(Collectors.joining(DELIMITER));
    }

    public static void printDealerAddCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayersResult(final Dealer dealer, final Users users) {
        System.out.println();
        printPlayerResult(dealer);
        users.forEach(OutputView::printPlayerResult);
    }

    private static void printPlayerResult(final Player player) {
        System.out.printf("%s카드: %s - 결과: %d\n", player.getName(), makeCardNames(player.getCards()), player.getScore());
    }

    public static void printLastResult(Results results) {
        System.out.println("\n## 최종 승패");
        for (Result result : results) {
            printIndividualResult(result);
        }
    }

    private static void printIndividualResult(final Result result) {
        if (result.isPlayCountMoreThanOne()) {
            System.out.printf("%s: %d승 %s패\n", result.getName(), result.getWinCount(), result.getLoseCount());
            return;
        }
        if (result.hasWin()) {
            System.out.println(result.getName() + ": 승");
            return;
        }
        System.out.println(result.getName() + ": 패");
    }
}
