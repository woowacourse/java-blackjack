package blackjack.view;

import blackjack.model.Dealer;
import blackjack.model.GameResult;
import blackjack.model.GameSummary;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.model.User;
import java.util.EnumMap;
import java.util.List;

public class OutputView {
    public static void printInitCards(List<Player> players, Dealer dealer) {
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
        System.out.println(
                "딜러카드: " + dealer.cards().getFirst().getRank().getName() + dealer.cards().getFirst().getSuit()
                        .getName());
    }

    public static void printPlayerCards(Player player) {
        List<String> formats = player.cards().stream()
                .map((card) -> {
                    return card.getRank().getName() + card.getSuit().getName();
                })
                .toList();

        System.out.println(player.getName() + "카드: " + String.join(", ", formats));
    }


    public static void printDealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printCardStatus(GameSummary gameSummary) {
        User user = gameSummary.user();

        StringBuilder sb = new StringBuilder();
        sb.append(user.getName() + "카드: ");
        List<String> cardFormats = user.cards().stream()
                .map((card) -> card.getSuit().getName() + card.getRank().getName()).toList();
        sb.append(String.join(", ", cardFormats));
        sb.append(" - 결과: " + gameSummary.score());

        System.out.println(sb);
    }

    public static void printGameResult(Players players, Dealer dealer) {
        System.out.println();
        System.out.println("## 최종 승패");

        EnumMap<GameResult, Integer> dealerGameResult = dealer.getGameResults();
        System.out.println(
                dealer.getName() + ": " + dealerGameResult.getOrDefault(GameResult.WIN, 0) + "승 " +
                        dealerGameResult.getOrDefault(GameResult.DRAW, 0) + "무 " + dealerGameResult.getOrDefault(
                        GameResult.LOSE, 0) + "패");

        for (Player player : players.all()) {
            System.out.print(player.getName() + ": ");
            System.out.println(player.getGameResult().getFormat());
        }
    }

    public static void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

}

