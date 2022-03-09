package view;

import domain.card.Card;
import domain.card.Cards;
import domain.game.GameResult;
import domain.game.MatchResult;
import domain.player.Dealer;
import domain.player.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {

    }

    public static void printInitialCards(Dealer dealer, List<Player> players) {
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), getPlayerNames(players));
        System.out.printf("%s: %s%n", dealer.getName(), getCardName(dealer.getCards().getValue().get(0)));
        for (Player player : players) {
            printCards(player);
        }
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(player -> player.getName().getValue())
                .collect(Collectors.joining(", "));
    }

    private static String getCardNames(Cards cards) {
        return cards.getValue().stream()
                .map(card -> getCardName(card))
                .collect(Collectors.joining(", "));
    }

    private static String getCardName(Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printCards(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), getCardNames(player.getCards()));
    }

    public static void printDealerDrawInfo() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardsResult(Dealer dealer, List<Player> players) {
        System.out.printf("%s 카드: %s - 결과: %d%n", dealer.getName(), getCardNames(dealer.getCards()), dealer.getCards().sum());
        for (Player player : players) {
            System.out.printf("%s 카드: %s - 결과: %d%n", player.getName(), getCardNames(player.getCards()), player.getCards().sum());
        }
    }

    public static void printGameResult(GameResult gameResult) {
        Map<Player, MatchResult> map = gameResult.getGameResult();
        System.out.printf("딜러: %d승 %d패%n", gameResult.getDealerWinCount(), gameResult.getDealerLoseCount());
        for (Player player : map.keySet()) {
            System.out.printf("%s: %s%n", player.getName(), gameResult.getMatchResult(player).getValue());
        }
    }
}
