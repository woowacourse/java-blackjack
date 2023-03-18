package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {

    public void printCardInitDrawResultMessage(final Players players, final Dealer dealer) {
        final List<String> playerNames = extractPlayerNames(players);
        final String dealerName = dealer.getName();

        System.out.println(dealerName + "와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public void printUserCards(final String name, final List<Card> cards) {
        System.out.print(System.lineSeparator() + name + "카드: " + getCards(cards));
    }

    private String getCards(final List<Card> cards) {
        return cards.stream().map(this::toCardView)
                .collect(Collectors.joining(", "));
    }

    private String toCardView(final Card card) {
        final CardNumber number = card.getNumber();
        final CardShape shape = card.getShape();

        return number.getName() + shape.getName();
    }

    private static List<String> extractPlayerNames(final Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void printDealerDraw() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }


    public void printCardResult(final User user) {
        printUserCards(user.getName(), user.getCards());
        System.out.println(" - 결과: " + user.getScore().getScore());
    }

    public void introduceProfitResult() {
        System.out.println("## 최종 수익");
    }

    public static void printBust() {
        System.out.println(System.lineSeparator() + "BUST 되었습니다.");
    }

    public void printUserProfit(final String name, final int value) {
        System.out.println(name + ": " + value);
    }
}
