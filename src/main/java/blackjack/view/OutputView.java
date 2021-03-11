package blackjack.view;

import blackjack.domain.*;
import blackjack.domain.card.Card;
import blackjack.util.BlackJackConstant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String COMMA_WITH_BLANK = ", ";

    private OutputView() {
    }

    public static void printInitialComment(Users users) {
        System.out.printf("%s와 %s에게 2장의 카드를 나누어주었습니다.\n", users.getDealer().getName(), createPlayersCardStringFormat(users.getPlayers()));
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

    public static void printResult(Map<User, Result> playerResult, Dealer dealer) {
        System.out.println("## 최종 수익");

        printDealerResult(playerResult, dealer);

        for(User user : playerResult.keySet()){
            if(playerResult.get(user) == Result.BLACKJACK) {
                System.out.printf("%s : %d\n", user.getName(), (user.getBettingMoney()*1.5));
            }

            if(playerResult.get(user) == Result.WIN || playerResult.get(user) == Result.DRAW) {
                System.out.printf("%s : %d\n", user.getName(), user.getBettingMoney());
            }

            if(playerResult.get(user) == Result.LOSE) {
                System.out.printf("%s : %d\n", user.getName(), -user.getBettingMoney());
            }
        }
    }

    private static void printDealerResult(Map<User, Result> playerResult, Dealer dealer) {
        int dealerTotalMoney = dealer.getBettingMoney();

        for(User user : playerResult.keySet()){
            if(playerResult.get(user) == Result.BLACKJACK) {
                dealerTotalMoney -= user.getBettingMoney() * 1.5;
            }

            if(playerResult.get(user) == Result.WIN || playerResult.get(user) == Result.DRAW) {
                dealerTotalMoney -= user.getBettingMoney();
            }
        }

        System.out.printf("딜러: %d \n", dealerTotalMoney);
    }

    public static void printDealerGetNewCardsMessage() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
        System.out.println();
    }
}
