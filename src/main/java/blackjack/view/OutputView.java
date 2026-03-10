package blackjack.view;

import static blackjack.model.constant.Constant.BLACKJACK_SCORE;

import blackjack.model.card.Card;
import blackjack.model.user.Dealer;
import blackjack.model.GameResult;
import blackjack.model.user.Player;
import blackjack.model.PlayersGameResult;
import blackjack.model.user.Users;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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
        Dealer dealer = users.getDealer();
        List<Player> players = users.getPlayers();
        StringBuilder sb = new StringBuilder();

        sb.append(dealer.getName()).append(" 카드: ");
        sb.append(dealer.cards().stream()
                .map(card -> card.getRank().getFormat() + card.getSuit().getFormat())
                .collect(Collectors.joining(", ")));
        sb.append(" - 결과: ").append(dealer.totalScore()).append("\n");

        for (Player player : players) {
            sb.append(player.getName()).append(" 카드: ");
            sb.append(player.cards().stream()
                    .map(card -> card.getRank().getFormat() + card.getSuit().getFormat())
                    .collect(Collectors.joining(", ")));
            sb.append(" - 결과: ").append(player.totalScore()).append("\n");
        }
        System.out.println(sb);
    }

    public static void printGameResult(PlayersGameResult playersGameResult, Users users) {
        System.out.println();
        System.out.println("## 최종 승패");

        Dealer dealer = users.getDealer();
        EnumMap<GameResult, Integer> dealerGameResult = dealer.getGameResults();
        System.out.println(
                dealer.getName() + ": " + dealerGameResult.getOrDefault(GameResult.WIN, 0) + "승 " +
                        dealerGameResult.getOrDefault(GameResult.DRAW, 0) + "무 " + dealerGameResult.getOrDefault(
                        GameResult.LOSE, 0) + "패");

        Map<Player, GameResult> result = playersGameResult.result();
        for (Player player : users.getPlayers()) {
            System.out.print(player.getName() + ": ");
            System.out.println(result.get(player).getFormat());
        }
    }

    public static void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
