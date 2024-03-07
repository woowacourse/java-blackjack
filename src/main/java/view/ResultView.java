package view;

import domain.card.Card;
import domain.game.DealerResult;
import domain.game.Game;
import domain.game.PlayerResults;
import domain.user.Dealer;
import domain.user.User;
import domain.user.UserDeck;
import domain.user.Users;

import java.util.stream.Collectors;

public class ResultView {

    public static void showStartStatus(Users users) {
        printUserNames(users);
        printUserCards(users);
        System.out.println();
    }

    private static void printUserNames(Users users) {
        String names = users.getPlayers()
                .stream()
                .map(player -> player.getName().value())
                .collect(Collectors.joining(", "));
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.%n", names);
    }

    private static void printUserCards(Users users) {
        Dealer dealer = users.getDealer();
        Card dealerVisibleCard = dealer.getVisibleCard();
        System.out.println(dealer.getName().value() + ": " + dealerVisibleCard.getInformation());
        users.getPlayers()
                .forEach(player -> System.out.println(joinUserNameAndDeck(player)));
    }

    public static void printPlayerAndDeck(User user) {
        System.out.println(joinUserNameAndDeck(user));
    }

    private static String joinUserNameAndDeck(User user) {
        return user.getName().value()
                + "카드: "
                + joinDeck(user.getUserDeck());
    }

    private static String joinDeck(UserDeck userDeck) {
        return userDeck.getCards()
                .stream()
                .map(Card::getInformation)
                .collect(Collectors.joining(", "));
    }

    public static void dealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showCardsAndSum(Users users) {
        System.out.println();
        users.getUsers()
                .forEach((user) ->
                        System.out.println(joinUserNameAndDeck(user)
                                + " - 결과: " + user.sumUserDeck()));
    }

    public static void showResult(Game game) {
        System.out.println("\n## 최종 승패");
        PlayerResults playerResults = game.generatePlayerResults();
        DealerResult dealerResult = playerResults.generateDealerResult();
        System.out.println("딜러: " + dealerResult.getInformation());

        playerResults.getPlayerResults()
                .forEach(((player, result) ->
                        System.out.println(player.getName().value() + ": " + result.getResult())));
    }

    public static void printBust(User user) {
        printPlayerAndDeck(user);
        System.out.println("버스트!");
    }
}
