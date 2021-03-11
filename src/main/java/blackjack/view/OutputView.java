package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.User;
import blackjack.domain.Users;
import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String COMMA_WITH_BLANK = ", ";

    private OutputView() {
    }

    public static void printInitialComment(Users users) {
        System.out.printf("%s와 %s에게 2장의 카드를 나누어주었습니다.\n",
                users.getDealer().getName(),
                createPlayersCardStringFormat(users.getPlayers())
        );
        for (User user : users.gerUsers()) {
            System.out.println(makeCardsStringFormat(user));
        }
        System.out.println();
    }

    public static void printCardsOfPlayersWithScore(Users users) {
        for (User user : users.gerUsers()) {
            System.out.print(makeCardsStringFormat(user) + " - 결과: " + makeResultComment(user) + "\n");
        }
        System.out.println();
    }

    private static String makeResultComment(User user) {
        if (user.isBlackJack()) {
            return "블랙잭";
        }
        if (user.isBust()) {
            return "버스트";
        }
        return Integer.toString(user.getScore());
    }

    public static void printCardsOfPlayer(Player player) {
        System.out.println(makeCardsStringFormat(player));
        System.out.println();
    }


    public static String makeCardsStringFormat(User user) {
        return String.format("%s 카드 : %s", user.getName(), createCardsStringFormat(user.getCards()));
    }

    private static String createCardsStringFormat(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
    }

    private static String createPlayersCardStringFormat(List<Player> players) {
        return players.stream()
                .map(User::getName)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
    }

    public static void printResult(Users users) {
        System.out.println("## 최종 수익");

        printDealerResult(users.getPlayers(), users.getDealer());

        for (User user : users.getPlayers()) {
            System.out.printf("%s : %.0f\n", user.getName(), user.profit(user.getBettingMoney(), users.getDealer()));
        }
    }

    private static void printDealerResult(List<Player> players, Dealer dealer) {
        int dealerTotalMoney = dealer.getBettingMoney();

        for (Player player : players) {
            dealerTotalMoney -= player.profit(player.getBettingMoney(), dealer);
        }

        System.out.printf("딜러: %d \n", dealerTotalMoney);
    }

    public static void printDealerGetNewCardsMessage() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
        System.out.println();
    }
}
