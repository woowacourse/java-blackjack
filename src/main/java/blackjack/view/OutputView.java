package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final Map<Shape, String> SHAPE_NAME = Map.of(
            Shape.HEART, "하트",
            Shape.SPADE, "스페이드",
            Shape.DIAMOND, "다이아몬드",
            Shape.CLOVER, "클로버"
    );

    private static final Map<Value, String> VALUE_NAME = Map.ofEntries(
            Map.entry(Value.ACE, "A"), Map.entry(Value.TWO, "2"),
            Map.entry(Value.THREE, "3"), Map.entry(Value.FOUR, "4"),
            Map.entry(Value.FIVE, "5"), Map.entry(Value.SIX, "6"),
            Map.entry(Value.SEVEN, "7"), Map.entry(Value.EIGHT, "8"),
            Map.entry(Value.NINE, "9"), Map.entry(Value.TEN, "10"),
            Map.entry(Value.JACK, "J"), Map.entry(Value.QUEEN, "Q"),
            Map.entry(Value.KING, "K")
    );

    public void printStartStatus(Dealer dealer, Players players) {
        System.out.println();
        System.out.println("딜러와 " + toPrintedFormat(players) + "에게 2장을 나누었습니다.");
        printDealerCards(dealer.getStartCards());
        System.out.println();
        printPlayersCards(players);
        System.out.println();
    }

    public void printEndingStatus(Dealer dealer, Players players) {
        System.out.println();
        System.out.println();
        printDealerEndingStatus(dealer);
        printPlayersEndingStatus(players);
        System.out.println();
    }

    private void printDealerCards(List<Card> cards) {
        System.out.print("딜러 카드: ");
        printCards(cards);
    }

    private void printPlayersCards(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerCards(player.getName(), player.getStartCards());
            System.out.println();
        }
    }

    private void printDealerEndingStatus(Dealer dealer) {
        printDealerCards(dealer.getCards());
        printScore(dealer.calculateScore());
        System.out.println();
    }

    private void printPlayerCards(Name name, List<Card> cards) {
        System.out.print(name.getName() + "카드: ");
        printCards(cards);
    }

    private void printCards(List<Card> cards) {
        String printingFormat = cards.stream()
                .map(this::toPrintedFormat)
                .collect(Collectors.joining(", "));
        System.out.print(printingFormat);
    }

    private void printPlayersEndingStatus(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerCards(player.getName(), player.getCards());
            printScore(player.calculateScore());
            System.out.println();
        }
    }

    public void printPlayerCards(Player player) {
        printPlayerCards(player.getName(), player.getCards());
        System.out.println();
    }

    public void printDealerDraw() {
        System.out.println();
        System.out.print("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private void printScore(int score) {
        System.out.print(" - 결과 : " + score);
    }

    public void printPrizeTitle() {
        System.out.println("## 최종 수익");
    }

    public void printDealerPrize(Money prize) {
        System.out.println("딜러: " + prize.getAmount());
    }

    public void printPlayerPrize(Name name, Money prize) {
        System.out.println(name.getName() + ": " + prize.getAmount());
    }

    private String toPrintedFormat(Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .map(Name::getName)
                .collect(Collectors.joining(", "));
    }

    private String toPrintedFormat(Card card) {
        return VALUE_NAME.get(card.getValue()) + SHAPE_NAME.get(card.getShape());
    }
}
