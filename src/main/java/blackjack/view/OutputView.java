package blackjack.view;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String BUST_MESSAGE = "버스트 되었습니다!!!";

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

    public static void printUserCard(User user) {
        String userCards = createUserCards(user);
        System.out.printf("%s : %s" + NEW_LINE, user.getName(), userCards);

        printIfBust(user);
    }

    private static String createUserCards(User user) {
        return user.getCards().stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    private static void printIfBust(User user) {
        if (user.isBust()) {
            System.out.println(BUST_MESSAGE);
        }
    }

    public static void printDealerTurn(Dealer dealer) {
        System.out.printf("딜러는 %s미만이라 한장의 카드를 더 받았습니다." + NEW_LINE,
                Dealer.MINIMUM_NUMBER_TO_STAY);

        printIfBust(dealer);
    }

    public static void printFinalInfo(User dealer, Players players) {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players.getPlayers());

        for (User user : users) {
            String userCards = createUserCards(user);
            String score = createUserScore(user);

            System.out.printf("%s : %s - 결과: %s" + NEW_LINE,
                    user.getName(), userCards, score);
        }
    }

    private static String createUserScore(User user) {
        if (user.isBust()) {
            return "bust";
        }
        return String.valueOf(user.calculateScore().getScore());
    }

    public static void printResult(Result result) {
        System.out.println(result.getDealerResult());

        Map<User, Boolean> playerResults = result.getPlayerResults();
        Set<User> players = playerResults.keySet();
        for (User player : players) {
            System.out.printf("%s : %s" + NEW_LINE, player.getName(), playerResults.get(player));
        }
    }
}
