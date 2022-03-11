package blackjack.view;

import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.Gamer;
import blackjack.model.Player;
import blackjack.model.Result;
import blackjack.model.cards.Cards;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {}

    public static void printOpenCard(Dealer dealer, List<Gamer> gamers) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.name(), gamerNames(gamers));
        System.out.printf("%s: %s%n", dealer.name(), openCards(dealer.openCards()));
        for (Player gamer : gamers) {
            System.out.printf("%s카드: %s%n", gamer.name(), openCards(gamer.openCards()));
        }
    }

    private static String gamerNames(List<Gamer> gamers) {
        return gamers.stream()
            .map(Player::name)
            .collect(Collectors.joining(", "));
    }

    private static String openCards(Cards openCard) {
        return openCard.stream()
            .map(OutputView::cardText)
            .collect(Collectors.joining(", "));
    }

    private static String cardText(Card card) {
        return card.rank().symbol() + card.suit().symbol();
    }

    public static void printCard(Player player) {
        System.out.printf("%s: %s%n", player.name(), takenCards(player));
    }

    private static String takenCards(Player player) {
        return player.cards().stream()
            .map(OutputView::cardText)
            .collect(Collectors.joining(", "));
    }

    public static void printDealerTakeCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printTotalScore(Dealer dealer, List<Gamer> gamers) {
        System.out.printf("%s 카드: %s - 결과: %d%n", dealer.name(), takenCards(dealer), dealer.score().getValue());
        for (Player player : gamers) {
            System.out.printf("%s 카드: %s - 결과: %d%n", player.name(), takenCards(player), player.score().getValue());
        }
    }

    public static void printDealerRecord(Map<Result, Integer> result) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d무 %d패%n",
                result.getOrDefault(Result.WIN, 0),
                result.getOrDefault(Result.DRAW, 0),
                result.getOrDefault(Result.LOSS, 0));
    }

    public static void printGamerRecord(String name, String result) {
        System.out.printf("%s: %s%n", name, result);
    }
}
