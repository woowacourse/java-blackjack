package view;

import domain.card.Card;
import domain.game.DealerResult;
import domain.game.PlayerResults;
import domain.user.Name;
import domain.user.Users;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    public static void showStartStatus(List<Name> names, Name dealerName, Card dealerCard, Map<Name, List<Card>> userCards) {
        printUserNames(names);
        printDealerCard(dealerName, dealerCard);
        printUserCards(userCards);
        System.out.println();
    }

    private static void printUserNames(List<Name> inputNames) {
        String names = inputNames
                .stream()
                .map(Name::value)
                .collect(Collectors.joining(", "));
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.%n", names);
    }


    private static void printDealerCard(Name name, Card card) {
        System.out.println(name.value() + ": " + card.getName());
    }

    private static void printUserCards(Map<Name, List<Card>> userCards) {
        userCards.forEach((name, cards) ->
                System.out.println(joinUserNameAndDeck(name, cards)));
    }

    public static void printPlayerAndDeck(Name name, List<Card> userDeck) {
        System.out.println(joinUserNameAndDeck(name, userDeck));
    }

    private static String joinUserNameAndDeck(Name name, List<Card> userDeck) {
        return name.value()
                + "카드: "
                + joinDeck(userDeck);
    }

    private static String joinDeck(List<Card> userDeck) {
        return userDeck.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public static void dealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showCardsAndSum(Users users) {
        System.out.println();
        users.getUsers()
                .forEach((user) ->
                        System.out.println(joinUserNameAndDeck(user.getName(), user.getUserDeck().getCards())
                                + " - 결과: " + user.sumUserDeck()));
    }

    public static void showResult(PlayerResults playerResults, DealerResult dealerResult) {
        System.out.println("\n## 최종 승패");
        System.out.println("딜러: " + dealerResult.getResultDetail());

        playerResults.getPlayerResults()
                .forEach(((player, result) ->
                        System.out.println(player.getName().value() + ": " + result.getResult())));
    }

    public static void printBust(Name name, List<Card> userDeck) {
        printPlayerAndDeck(name, userDeck);
        System.out.println("버스트!");
    }
}
