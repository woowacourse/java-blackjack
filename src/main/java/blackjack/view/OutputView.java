package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import blackjack.domain.result.GameResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {}

    public static void println(String value) {
        System.out.println(value);
    }

    public static void printGameStartMessage(Dealer dealer, List<Gamer> gamers) {
        String dealerName = dealer.getName();
        String gamerNames = gamers.stream()
            .map(gamer -> gamer.getName())
            .collect(Collectors.joining(", "));
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealerName, gamerNames);
        printDealerCardInfo(dealer);
        for (Gamer gamer : gamers) {
            printPlayerCardInfo(gamer);
        }
    }

    private static void printDealerCardInfo(Dealer dealer) {
        Card card = dealer.getCards().getFirstCard();
        System.out.println(dealer.getName() + ": " + cardToString(card));
    }

    public static void printPlayerCardInfo(Player player) {
        System.out.println(playerInfoToString(player));
    }

    private static String cardToString(Card card) {
        final String demomination = card.getDenomination().getName();
        final String shape = card.getShape().getName();
        return demomination + shape;
    }

    private static String playerInfoToString(Player player) {
        final String playerName = player.getName();
        final String playerCardInfo = player.getCards().getCards()
            .stream()
            .map(card -> cardToString(card))
            .collect(Collectors.joining(", "));
        return playerName + " 카드: " + playerCardInfo;
    }

    public static void printDealerOnemoreDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayersScoreInfo(Dealer dealer, List<Gamer> gamers) {
        System.out.printf("%s - 결과: %d\n", playerInfoToString(dealer), dealer.calculateScore());
        gamers.stream()
            .forEach(gamer -> System.out.printf("%s - 결과: %d\n", playerInfoToString(gamer), gamer.calculateScore()));
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println("## 최종 승패");

    }
}
