package blackjack.view;

import blackjack.domain.playing.card.Card;
import blackjack.domain.playing.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String JOINING_DELIMITER = ", ";
    private static final String NEW_LINE = System.lineSeparator();
    private static final int DEALER_CARDS_COUNT_TO_SHOW = 1;
    private static final String BUST_MESSAGE = "버스트 되었습니다!!!";

    public static void printInitialInfo(Dealer dealer, Players players) {
        printInitialInfoHead(dealer, players);

        printInitialDealerCard(dealer);
        printAllPlayerCards(players);
    }

    private static void printInitialInfoHead(Dealer dealer, Players players) {
        String playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(JOINING_DELIMITER));

        System.out.printf("%s와 %s에게 %d장을 나누었습니다." + NEW_LINE,
                dealer.getName(), playerNames, Dealer.DRAWING_NUMBER_INITIALLY);
    }

    private static void printInitialDealerCard(Dealer dealer) {
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

    public static void printUserCard(AbstractUser abstractUser) {
        String userCards = createUserCards(abstractUser);
        System.out.printf("%s : %s" + NEW_LINE, abstractUser.getName(), userCards);

        printIfBust(abstractUser);
    }

    private static String createUserCards(AbstractUser abstractUser) {
        return abstractUser.getCards().stream()
                .map(Card::getName)
                .collect(Collectors.joining(JOINING_DELIMITER));
    }

    private static void printIfBust(AbstractUser abstractUser) {
        if (abstractUser.isBust()) {
            System.out.println(BUST_MESSAGE);
        }
    }

    public static void printDealerTurn(Dealer dealer) {
        System.out.printf("딜러는 %s미만이라 한장의 카드를 더 받았습니다." + NEW_LINE,
                Dealer.MINIMUM_NUMBER_TO_STAY);

        printIfBust(dealer);
    }

    public static void printFinalInfo(Dealer dealer, Players players) {
        List<AbstractUser> abstractUsers = new ArrayList<>();
        abstractUsers.add(dealer);
        abstractUsers.addAll(players.getPlayers());

        for (AbstractUser abstractUser : abstractUsers) {
            String userCards = createUserCards(abstractUser);
            String score = createUserScore(abstractUser);

            System.out.printf("%s : %s - 결과: %s" + NEW_LINE,
                    abstractUser.getName(), userCards, score);
        }
    }

    private static String createUserScore(User user) {
        return String.valueOf(user.calculateScore().getScore());
    }

//    public static void printGameResult(GameResult gameResult) {
//        printDealerResults(gameResult);
//        printPlayerResults(gameResult);
//    }
//
//    private static void printPlayerResults(GameResult gameResult) {
//        Map<Player, ResultType> playerResults = gameResult.getPlayerResults();
//        Set<Player> players = playerResults.keySet();
//        for (Player player : players) {
//            ResultType resultType = playerResults.get(player);
//            System.out.printf("%s : %s" + NEW_LINE, player.getName(), resultType.getName());
//        }
//    }
//
//    private static void printDealerResults(GameResult gameResult) {
//        Map<ResultType, Integer> dealerResults = gameResult.getDealerResults();
//
//        int dealerWinCount = dealerResults.get(ResultType.WIN);
//        int dealerDrawCount = dealerResults.get(ResultType.DRAW);
//        int dealerLoseCount = dealerResults.get(ResultType.LOSE);
//
//        System.out.printf("%s : %d승 %d무 %d패" + NEW_LINE, Dealer.NAME, dealerWinCount, dealerDrawCount, dealerLoseCount);
//    }
}
