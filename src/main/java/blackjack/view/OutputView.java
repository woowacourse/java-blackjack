package blackjack.view;

import static blackjack.model.Constant.BLACKJACK_SCORE;

import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.GameResult;
import blackjack.model.Player;
import blackjack.model.User;
import blackjack.model.Users;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitCards(Users users) {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();

        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("딜러와 ");
        sb.append(String.join(", ", names));
        sb.append("에게 2장을 나누었습니다.");
        System.out.println(sb);

        printDealerCard(dealer);
        for (Player player : players) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    private static void printDealerCard(Dealer dealer) {
        Card firstCard = dealer.cards().getFirst();
        System.out.println("딜러카드: " + firstCard.getRank().getFormat() + firstCard.getSuit().getFormat());
    }

    public static void printPlayerCards(Player player) {
        String cardsFormat = player.cards().stream()
                .map(card -> card.getRank().getFormat() + card.getSuit().getFormat())
                .collect(Collectors.joining(", "));

        System.out.println(player.getName() + "카드: " + cardsFormat);
    }

    public static void printCantHit() {
        System.out.println("카드의 합계가 " + BLACKJACK_SCORE + " 이상입니다. 더이상 카드를 받을 수 없습니다.");
    }

    public static void printDealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printHandStatus(Users users) {
        StringBuilder sb = new StringBuilder();
        for (User user : users.getUsers()) {
            sb.append(user.getName()).append(" 카드: ");
            sb.append(user.cards().stream()
                    .map(card -> card.getRank().getFormat() + card.getSuit().getFormat())
                    .collect(Collectors.joining(", ")));
            sb.append(" - 결과: ").append(user.totalScore()).append("\n");
        }
        System.out.println(sb);
    }

    public static void printGameResult(Users users) {
        System.out.println();
        System.out.println("## 최종 승패");

        Dealer dealer = users.getDealer();
        EnumMap<GameResult, Integer> dealerGameResult = dealer.getGameResults();
        System.out.println(
                dealer.getName() + ": " + dealerGameResult.getOrDefault(GameResult.WIN, 0) + "승 " +
                        dealerGameResult.getOrDefault(GameResult.DRAW, 0) + "무 " + dealerGameResult.getOrDefault(
                        GameResult.LOSE, 0) + "패");

        List<Player> players = users.getPlayers();
        for (Player player : players) {
            System.out.print(player.getName() + ": ");
            System.out.println(player.getGameResult().getFormat());
        }
    }

    public static void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
