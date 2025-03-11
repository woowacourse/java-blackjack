package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import blackjack.model.card.Cards;
import blackjack.model.game.GameResult;
import blackjack.model.player.Player;

public class OutputView {

    public void printDealInitialCardsResult(final List<Player> players, final List<Cards> openCards) {
        String userNames = players.stream()
                .skip(1L)
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        String dealerName = players.getFirst().getName();
        System.out.println();
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealerName, userNames);
        IntStream.range(0, players.size())
                .forEachOrdered(index -> printPlayerCards(players.get(index), openCards.get(index)));
        System.out.println();
    }

    public void printPlayerCards(final Player player, final Cards cards) {
        System.out.printf("%s카드: %s%n", player.getName(), formatCards(cards));
    }

    private String formatCards(final Cards cards) {
        return cards.getValues()
                .stream()
                .map(card -> card.cardNumber().getName() + card.cardType().getName())
                .collect(Collectors.joining(", "));
    }

    public void printDealerDrawnMoreCards(boolean isDrawn) {
        System.out.println();
        if (isDrawn) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
            return;
        }
        System.out.println("딜러는 한장의 카드를 더 받지 않았습니다." + System.lineSeparator());
    }

    public void printOptimalPoints(final Map<Player, Integer> optimalPoints) {
        optimalPoints.forEach((player, value) -> System.out.printf(
                "%s카드: %s - 결과: %d%n", player.getName(), formatCards(player.getCards()), value
        ));
        System.out.println();
    }

    public void printGameResult(final Map<Player, Map<GameResult, Integer>> gameResults) {
        System.out.println("## 최종 승패");
        gameResults.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ": " + formatResults(entry.getValue()))
                .forEach(System.out::println);
    }

    private String formatResults(Map<GameResult, Integer> resultStatistics) {
        boolean hasMultipleResults = resultStatistics.values().stream().mapToInt(integer -> integer).sum() > 1;
        if (hasMultipleResults) {
            return resultStatistics.entrySet().stream()
                    .filter(entry -> entry.getValue() > 0)
                    .map(entry -> entry.getValue() + entry.getKey().getName())
                    .collect(Collectors.joining(" "));
        }
        return resultStatistics.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getKey().getName())
                .collect(Collectors.joining());
    }

}
