package view;

import domain.card.Card;
import domain.game.Result;
import domain.game.Results;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Player;
import domain.player.Users;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String INITIAL_DISTRIBUTE_COUNT = "2";

    public static void printDistributeMessage(final Users users) {
        List<String> usersName = new ArrayList<>();

        users.forEach(user -> usersName.add(user.getName()));
        System.out.printf("\n딜러와 %s에게 %s장 나누었습니다.\n", String.join(DELIMITER, usersName), INITIAL_DISTRIBUTE_COUNT);
    }

    public static void printInitialStatus(final Dealer dealer, final Users users) {
        printStatus(dealer);
        users.forEach(user -> printStatus(user));
        System.out.println();
    }

    public static void printStatus(final Player player) {
        if (player instanceof Dealer) {
            System.out.printf("%s카드: %s\n", player.getName(), ((Dealer) player).getFirstCardInfo());
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
        System.out.println("\n## 최종 수익");
        Set<Map.Entry<Name, Result>> entries = results.getResults()
                .entrySet();

        for (Map.Entry<Name, Result> entry : entries) {
            printIndividualResult(entry);
        }
    }

    private static void printIndividualResult(final Map.Entry<Name, Result> entry) {
        Name name = entry.getKey();
        Result result = entry.getValue();

        System.out.println(name.getValue() + ": " + result.getWinningMoney()
                .setScale(0, RoundingMode.FLOOR));
    }
}
