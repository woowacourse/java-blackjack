package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.model.card.Cards;
import blackjack.model.game.BlackJackRule;
import blackjack.model.game.Result;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;

public class OutputView {

    public void printDealInitialCardsResult(final Dealer dealer, final List<User> users,
                                            final BlackJackRule blackJackRule) {
        String userNames = users.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.println();
        System.out.println(dealer.getName() + "와 " + userNames + "에게 2장을 나누었습니다.");
        System.out.println(dealer.getName() + "카드: " + formatCards(blackJackRule.openInitialCards(dealer)));
        users.forEach(user -> printPlayerCards(user, blackJackRule));
        System.out.println();
    }

    public void printPlayerCards(final Player player, final BlackJackRule blackJackRule) {
        System.out.println(player.getName() + "카드: " + formatCards(blackJackRule.openInitialCards(player)));
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

    public void printOptimalPoints(final Dealer dealer, final List<User> users, final BlackJackRule blackJackRule) {
        System.out.println(
                dealer.getName() + "카드: " + formatCards(blackJackRule.openAllCards(dealer)) + " - 결과: "
                        + blackJackRule.calculateOptimalPoint(
                        dealer));
        users.forEach(user -> System.out.println(
                user.getName() + "카드: " + formatCards(blackJackRule.openAllCards(user)) + " - 결과: "
                        + blackJackRule.calculateOptimalPoint(
                        user)));
        System.out.println();
    }

    public void printGameResult(final Map<Player, Map<Result, Integer>> playerListMap) {
        System.out.println("## 최종 승패");
        playerListMap.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ": " + formatResults(entry.getValue()))
                .forEach(System.out::println);
    }

    private String formatResults(Map<Result, Integer> resultStatistics) {
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
