package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
    private static final String JOINING_DELIMITER = ", ";
    private static final String NEW_LINE = System.lineSeparator();
    private static final int DEALER_CARDS_COUNT_TO_SHOW = 1;
    private static final String BUST_MESSAGE = "버스트 되었습니다!!!";

    public static void printInitialInfo(User dealer, Players players) {
        printInitialInfoHead(dealer, players);

        printInitialDealerCard(dealer);
        printAllPlayerCards(players);
    }

    private static void printInitialInfoHead(User dealer, Players players) {
        String playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(JOINING_DELIMITER));

        System.out.printf("%s와 %s에게 %d장을 나누었습니다." + NEW_LINE,
                dealer.getName(), playerNames, dealer.countCards());
    }

    private static void printInitialDealerCard(User dealer) {
        String dealerCards = dealer.getCards().stream()
                .limit(DEALER_CARDS_COUNT_TO_SHOW)
                .map(Card::getName)
                .collect(Collectors.joining(JOINING_DELIMITER));

        System.out.printf("%s : %s" + NEW_LINE, dealer.getName(), dealerCards);
    }

    private static void printAllPlayerCards(Players players) {
        for (Player player : players.getPlayers()) {
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
                .collect(Collectors.joining(JOINING_DELIMITER));
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

    public static void printFinalInfo(Dealer dealer, Players players) {
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
        return String.valueOf(user.calculateScore().getScore());
    }

    public static void printGameResult(GameResult gameResult) {
        printDealerResults(gameResult);
        printPlayerResults(gameResult);
    }

    private static void printPlayerResults(GameResult gameResult) {
        Map<Player, ResultType> playerResults = gameResult.getPlayerResults();
        Set<Player> players = playerResults.keySet();
        for (Player player : players) {
            ResultType resultType = playerResults.get(player);
            System.out.printf("%s : %s" + NEW_LINE, player.getName(), resultType.getName());
        }
    }

    private static void printDealerResults(GameResult gameResult) {
        long dealerWinCount = gameResult.getDealerWinCount();
        long dealerLoseCount = gameResult.getDealerLoseCount();

        System.out.printf("%s : %d승 %d패" + NEW_LINE, Dealer.NAME, dealerWinCount, dealerLoseCount);
    }
}
