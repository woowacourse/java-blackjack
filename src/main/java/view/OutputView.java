package view;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.Result;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEXT_LINE = System.lineSeparator();

    public void printInitialCards(Dealer dealer, List<Player> players) {
        String playerNames = players.stream().map(Player::getName).collect(Collectors.joining(", "));
        System.out.printf(NEXT_LINE + "%s와 %s에게 2장을 나누었습니다.%s", dealer.getName(), playerNames, NEXT_LINE);

        System.out.printf("%s카드: %s%n", dealer.getName(), formatSingleCard(dealer.getCards().getCards().getFirst()));

        players.forEach(this::printCards);
        System.out.println();
    }

    public void printCards(Player player) {
        cardFormmat(player);
        System.out.println();
    }

    private void cardFormmat(Player player) {
        String cards = player.getCards().getCards().stream().map(this::formatSingleCard)
                .collect(Collectors.joining(", "));
        System.out.printf("%s카드: %s", player.getName(), cards);
    }

    private String formatSingleCard(Card card) {
        return String.format("%s%s", card.getNumber().getName(), card.getSymbol().getName());
    }

    public void printDealerHitSuccess() {
        System.out.println(NEXT_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEXT_LINE);
    }


    public void printResult(Dealer dealer, List<Player> players) {
        cardFormmat(dealer);
        printScore(dealer);

        for (Player player : players) {
            cardFormmat(player);
            printScore(player);
        }

        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        Map<Player, Result> playerResult = new HashMap<>();
        for (Player player : players) {
            Result result = Result.judge(dealer.getCards(), player.getCards());
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
            playerResult.put(player, Result.judge(player.getCards(), dealer.getCards()));
        }

        System.out.println(NEXT_LINE + "## 최종승패");
        System.out.printf("%s: ", dealer.getName());
        for (Result result : Result.values()) {
            if (!dealerResult.containsKey(result)) {
                continue;
            }
            System.out.printf("%d%s ", dealerResult.get(result), result.getState());
        }
        System.out.println();

        for (Player player : players) {
            System.out.printf("%s: %s%n", player.getName(), playerResult.get(player).getState());
        }
    }

    private void printScore(Player player) {
        System.out.printf(" - 결과: %d%n", player.getScore());
    }
}
