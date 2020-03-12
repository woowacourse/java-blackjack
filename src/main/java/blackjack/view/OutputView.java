package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    public static void printInitialInfo(User dealer, Players players) {
        printInitialInfoHead(dealer, players);

        printInitialDealerCard(dealer);
        printAllPlayerCards(players);
    }

    private static void printInitialInfoHead(User dealer, Players players) {
        String playerNames = players.getPlayers().stream()
                .map(User::getName)
                .collect(Collectors.joining(", "));

        System.out.printf("%s와 %s에게 %d장을 나누었습니다." + NEW_LINE,
                dealer.getName(), playerNames, dealer.countCards());
    }

    private static void printInitialDealerCard(User dealer) {
        String dealerCards = dealer.getCards().stream()
                .limit(1)
                .map(Card::getName)
                .collect(Collectors.joining(", "));

        System.out.printf("%s : %s" + NEW_LINE, dealer.getName(), dealerCards);
    }

    private static void printAllPlayerCards(Players players) {
        for (User player : players.getPlayers()) {
            printUserCard(player);
        }
    }

    private static void printUserCard(User user) {
        String userCards = user.getCards().stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));

        System.out.printf("%s : %s" + NEW_LINE, user.getName(), userCards);
    }
}
