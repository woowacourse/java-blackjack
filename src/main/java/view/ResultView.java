package view;

import domain.Game;
import domain.Result;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.UserDeck;
import domain.user.Users;

import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    public static void showProcess(Users users) {
        String names = users.getPlayers()
                .stream()
                .map(player -> player.getName().value())
                .collect(Collectors.joining(", "));
        System.out.println("\n딜러와 %s에게 2장을 나누었습니다.".formatted(names));

        Card dealerVisibleCard = users.getDealerVisibleCard();
        System.out.println("딜러: " + dealerVisibleCard.getName());
        users.getPlayers()
                .forEach(player -> System.out.println(joinPlayerNameAndDeck(player)));
    }

    public static void printPlayerAndDeck(Player player) {
        System.out.println(joinPlayerNameAndDeck(player));
    }

    private static String joinPlayerNameAndDeck(Player player) {
        return player.getName().value()
                + "카드: "
                + joinDeck(player.getUserDeck());
    }

    private static String joinDeck(UserDeck userDeck) {
        return userDeck.getCards()
                .stream()
                .map(card -> card.getName())
                .collect(Collectors.joining(", "));
    }

    public static void dealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showCardsAndSum(Users users) {
        Dealer dealer = users.getDealer();
        System.out.println("딜러: " + joinDeck(dealer.getUserDeck()) + " - 결과: " + dealer.sumUserDeck());

        users.getPlayers()
                .forEach(player -> System.out.println(
                        joinPlayerNameAndDeck(player) + " - 결과: " + player.sumUserDeck()));
    }

    public static void showResult(Game game) {
        System.out.println("## 최종 승패");
        Map<Result, Integer> dealerResult = game.generateDealerResult();
        System.out.println("딜러: " + dealerResult.get(Result.WIN) + "승 " + dealerResult.get(Result.LOSE) + "패");

        Map<Player, Result> playerResult = game.generatePlayerResults();
        playerResult.forEach(((player, result) ->
                System.out.println(player.getName().value() + ": " + result.getResult())));
    }
}
