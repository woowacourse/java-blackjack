package blackjack.view;

import blackjack.model.card.Cards;
import blackjack.model.game.ResultStatistic;
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

    public void printGameResult(final Map<Player, ResultStatistic> playerListMap) {
        System.out.println("## 최종 승패");
        playerListMap.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ": " + formatResults(entry.getValue()))
                .forEach(System.out::println);
    }

    private String formatResults(ResultStatistic resultStatistic) {
        if (resultStatistic.hasMultipleResult()) {
            return resultStatistic.getStatistic().entrySet().stream()
                    .filter(entry -> entry.getValue().hasMeaningfulValue())
                    .map(entry -> entry.getValue().getValue() + entry.getKey().getName())
                    .collect(Collectors.joining(" "));
        }
        return resultStatistic.getStatistic().entrySet().stream()
                .filter(entry -> entry.getValue().hasMeaningfulValue())
                .map(entry -> entry.getKey().getName())
                .collect(Collectors.joining());
    }

}
