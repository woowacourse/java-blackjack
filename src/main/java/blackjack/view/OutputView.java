package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Score;
import blackjack.domain.ScoreResult;
import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class OutputView {

    public static void printPlayersCard(Players players, Player dealer) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(),
            players.getValue().stream().map(Player::getName).collect(Collectors.joining(", ")));
        printOpenCard(players.getValue(), dealer);
    }

    private static void printOpenCard(List<Player> players, Player dealer) {
        Card dealerOpenCard = dealer.getHoldCards().get(0);
        System.out.printf("%s: %s%s%n", dealer.getName(), dealerOpenCard.getCardNumber().getName(),
            dealerOpenCard.getPatternName());
        for (Player player : players) {
            printPlayerCards(player);
        }
    }

    private static void printPlayerResult(Player player) {
        System.out.printf("%s: %s - 결과: %d%n",
            player.getName(),
            player.getHoldCards()
                .stream()
                .map(card -> card.getCardNumber().getName() + card.getPatternName())
                .collect(Collectors.joining(", ")),
            player.getTotalNumber());
    }

    public static void printPlayersResult(Players players, Player dealer) {
        System.out.println();
        printPlayerResult(dealer);

        for (Player player : players.getValue()) {
            printPlayerResult(player);
        }
    }

    public static void printPlayerCards(Player player) {
        System.out.printf("%s: %s%n", player.getName(),
            player.getHoldCards()
                .stream()
                .map(card -> card.getCardNumber().getName() + card.getPatternName())
                .collect(Collectors.joining(", ")));
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printScoreResult(ScoreResult result) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d무 %d패%n",
            result.getDealerScoreCount(Score.WIN),
            result.getDealerScoreCount(Score.DRAW),
            result.getDealerScoreCount(Score.LOSE));

        for (Map.Entry<String, String> entry : result.getPlayerResult().entrySet()) {
            System.out.printf("%s: %s%n", entry.getKey(), entry.getValue());
        }
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
