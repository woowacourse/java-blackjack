package blackjack.view;

import blackjack.model.card.Cards;
import blackjack.model.game.Result;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printDealInitialCardsResult(final Dealer dealer, final List<User> users) {
        String userNames = users.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.println();
        System.out.println(dealer.getName() + "와 " + userNames + "에게 2장을 나누었습니다.");
        printPlayerCards(dealer);
        users.forEach(this::printPlayerCards);
        System.out.println();
    }

    public void printPlayerCards(final Player player) {
        System.out.println(player.getName() + "카드: " + formatCards(player.openInitialCards()));
    }

    private String formatCards(final Cards cards) {
        return cards.getValues()
                .stream()
                .map(card -> card.cardNumber().getName() + card.cardType().getName())
                .collect(Collectors.joining(", "));
    }

    public void printDealerDrawnMoreCards(final boolean isDrawn) {
        System.out.println();
        if (isDrawn) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
            return;
        }
        System.out.println("딜러는 한장의 카드를 더 받지 않았습니다." + System.lineSeparator());
    }

    public void printOptimalPoints(final Dealer dealer, final List<User> users) {
        System.out.println(
                dealer.getName() + "카드: " + formatCards(dealer.openAllCards()) + " - 결과: "
                        + dealer.calculateOptimalPoint());
        users.forEach(user -> System.out.println(
                user.getName() + "카드: " + formatCards(user.openAllCards()) + " - 결과: " + user.calculateOptimalPoint()));
        System.out.println();
    }

    public void printGameResult(final Map<Result, Integer> dealerResult, final Map<User, Result> usersResults) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerResult);
        printUsersResults(usersResults);
    }

    private void printDealerResult(final Map<Result, Integer> dealerResult) {
        System.out.println("딜러: " + formatDealerResult(dealerResult));
    }

    private String formatDealerResult(final Map<Result, Integer> dealerResult) {
        return dealerResult.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getValue() + entry.getKey().getName())
                .collect(Collectors.joining(" "));
    }

    private void printUsersResults(final Map<User, Result> userResults) {
        userResults.forEach(this::printUserResult);
    }

    private void printUserResult(final User user, final Result result) {
        System.out.println(user.getName() + ": " + result.getName());
    }
}
