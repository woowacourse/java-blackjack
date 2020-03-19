package blackjack.view;

import blackjack.domain.playing.card.Card;
import blackjack.domain.playing.user.AbstractUser;
import blackjack.domain.playing.user.Dealer;
import blackjack.domain.playing.user.Player;
import blackjack.domain.playing.user.Players;
import blackjack.domain.result.Profit;
import blackjack.domain.result.ProfitResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    private static String createUserScore(AbstractUser abstractUser) {
        return String.valueOf(abstractUser.calculateScore().getScore());
    }

    public static void printProfitResult(ProfitResult profitResult) {
        printDealerProfit(profitResult);
        printPlayersProfit(profitResult);
    }

    private static void printDealerProfit(ProfitResult profitResult) {
        Profit dealerProfit = profitResult.getDealerProfit();
        System.out.printf("%s : %s" + NEW_LINE, Dealer.NAME, dealerProfit.getProfit());
    }

    private static void printPlayersProfit(ProfitResult profitResult) {
        Map<Player, Profit> playersProfit = profitResult.getPlayerProfit();
        for (Player player : playersProfit.keySet()) {
            Profit profit = playersProfit.get(player);
            System.out.printf("%s : %s" + NEW_LINE, player.getName(), profit.getProfit());
        }
    }
}
