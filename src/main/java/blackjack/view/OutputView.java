package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printStartInfo(Dealer dealer, List<Player> players) {
        String names = players.stream()
                .map(Player::getName)
                .collect(joining(", "));
        System.out.println("\n딜러와 " + names + "에게 2장씩 나누었습니다.");

        System.out.println("딜러: " + cardInfo(dealer.getCards().get(0)));
        for (Player player : players) {
            printPlayerCardInfo(player);
        }
        System.out.println();
    }

    public static void printPlayerCardInfo(Player player) {
        String cardsInfo = player.getCards().stream()
                .map(card -> cardInfo(card))
                .collect(joining(", "));

        System.out.println(player.getName() + ": " + cardsInfo);
    }

    private static String cardInfo(Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printDealerDrawableInfo() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultInfo(Dealer dealer, List<Player> players) {
        System.out.println();
        createDealerResultInfo(dealer);
        for (Player player : players) {
            createPlayerResultInfo(player);
        }
    }

    private static void createPlayerResultInfo(Player player) {
        String cardsInfo = player.getCards().stream()
                .map(card -> cardInfo(card))
                .collect(joining(", "));

        System.out.println(player.getName() + ": " + cardsInfo + " - 결과: " + player.getTotalScore());
    }

    private static void createDealerResultInfo(Dealer dealer) {
        String cardsInfo = dealer.getCards().stream()
                .map(card -> cardInfo(card))
                .collect(joining(", "));

        System.out.println("딜러: " + cardsInfo + " - 결과: " + dealer.getTotalScore());
    }

    public static void printDealerGameResult(Map<GameResult, Long> result) {
        Map<GameResult, Long> results = new EnumMap<>(result);
        String resultInfo = results.keySet().stream()
                .map(score -> results.get(score) + score.getValue())
                .collect(Collectors.joining(" "));

        System.out.printf("\n## 최종 승패\n딜러: %s\n", resultInfo);
    }

    public static void printPlayerGameResult(Map<String, GameResult> results) {
        String resultInfo = results.keySet()
                .stream()
                .map(name -> name + ": " + results.get(name).getValue())
                .collect(Collectors.joining("\n"));

        System.out.println(resultInfo);
    }
}

