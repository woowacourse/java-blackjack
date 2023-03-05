package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import blackjack.domain.result.ResultMatcher;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printReadyMessage(List<Name> names) {
        String allName = names.stream().map(Name::getName).collect(Collectors.joining(", "));
        System.out.println("\n딜러와 " + allName + "에게 2장을 나누었습니다.");
    }

    public static void printPlayersCurrentCards(List<Player> players) {
        for (Player player : players) {
            printPlayerCurrentCards(player);
        }
    }

    public static void printPlayerCurrentCards(Player player) {
        System.out.println(player.getPlayerName() + " 카드: " + getUserCards(player));
    }

    public static void printDealerFirstCard(Dealer dealer) {
        Card firstCard = dealer.getPlayerCards().get(0);
        System.out.println(dealer.getDealerName() + " 카드: " + firstCard.getCardNumber().getNumber() + firstCard.getSymbol());
    }

    // 딜러와 플레이어의 카드와 최종 결과를 출력한다.
    public static void printResults(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.println(dealer.getDealerName() + "카드: " + getUserCards(dealer) + " - 결과: " + dealer.getTotalScore());
        for (Player player : players) {
            System.out.println(player.getPlayerName() + "카드: " + getUserCards(player) + " - 결과: " + player.getTotalScore());
        }
    }

    // 최종 결과를 통한 최종 승패를 출력한다.
    public static void printScore(Map<Player, ResultMatcher> playersScore, EnumMap<ResultMatcher, Integer> dealerScore) {
        System.out.println("\n## 최종 승패");
        System.out.print("딜러: ");
        for (Map.Entry<ResultMatcher, Integer> score : dealerScore.entrySet()) {
            if (score.getValue() > 0) {
                System.out.print(score.getKey().getResult() + score.getValue() + " ");
            }
        }
        System.out.println();
        for (Map.Entry<Player, ResultMatcher> score : playersScore.entrySet()) {
            System.out.println(score.getKey().getPlayerName() + ": " + score.getValue().getResult());
        }
    }

    private static String getUserCards(User user) {
        return user.getPlayerCards().stream()
                .map(card -> card.getCardNumber().getNumber() + card.getSymbol())
                .collect(Collectors.joining(", "));
    }

    public static void printDealerOneMore() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
